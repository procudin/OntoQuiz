package org.vstu.compprehension.controllers;


import lombok.var;
import org.apache.commons.lang3.math.NumberUtils;
import org.vstu.compprehension.Service.ExerciseService;
import org.vstu.compprehension.Service.UserService;
import org.vstu.compprehension.dto.*;
import org.vstu.compprehension.models.repository.ExerciseAttemptRepository;
import lombok.val;
import org.imsglobal.lti.launch.LtiOauthVerifier;
import org.imsglobal.lti.launch.LtiVerificationResult;
import org.imsglobal.lti.launch.LtiVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.vstu.compprehension.utils.Mapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("lti")
public class LtiExerciseController extends BasicExerciseController {
    @Value("${config.property.lti_launch_secret}")
    private String ltiLaunchSecret;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/pages/exercise" }, method = { RequestMethod.POST })
    public String ltiLaunch(Model model, HttpServletRequest request, @RequestParam Map<String, String> params) throws Exception {
        LtiVerifier ltiVerifier = new LtiOauthVerifier();
        String secret = this.ltiLaunchSecret;
        LtiVerificationResult ltiResult = ltiVerifier.verify(request, secret);
        if (!ltiResult.getSuccess()) {
            throw new Exception("Invalid LTI session");
        }

        var session = request.getSession();
        if (!session.isNew()) {
            session.invalidate();
            session = request.getSession();
        }
        session.setAttribute("ltiSessionInfo", params);

        val exerciseIdS = params.getOrDefault("custom_exerciseId", "-1");
        val exerciseId = NumberUtils.toLong(exerciseIdS, -1L);
        if (exerciseId == -1) {
            throw new Exception("Param 'custom_exerciseId' is required");
        }

        return super.launch(exerciseId, request);
    }

    @Override
    public UserInfoDto getCurrentUser(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        val currentUserInfo = (UserInfoDto)session.getAttribute("currentUserInfo");
        if (currentUserInfo != null) {
            return currentUserInfo;
        }

        val params = (Map<String, String>) session.getAttribute("ltiSessionInfo");
        if (params == null) {
            throw new Exception("Couldn't get lti session info");
        }
        val userEntity = userService.createOrUpdateFromLti(params);
        val userEntityDto = Mapper.toDto(userEntity);
        session.setAttribute("currentUserInfo", userEntityDto);
        return userEntityDto;
    }

    @Override
    public SessionInfoDto loadSessionInfo(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        val currentSessionInfo = (SessionInfoDto)session.getAttribute("sessionInfo");
        if (currentSessionInfo != null) {
            return currentSessionInfo;
        }

        val ltiParams = (Map<String, String>) session.getAttribute("ltiSessionInfo");
        if (ltiParams == null) {
            throw new Exception("Couldn't get session info");
        }
        val exerciseId = (Long)session.getAttribute("exerciseId");
        val user = getCurrentUser(request);
        val language = ltiParams.getOrDefault("launch_presentation_locale", "EN").toUpperCase();
        val sessionInfo = SessionInfoDto.builder()
                .sessionId(session.getId())
                .exerciseId(exerciseId)
                .user(user)
                .language(language)
                .build();
        session.setAttribute("sessionInfo", sessionInfo);

        return sessionInfo;
    }
}
