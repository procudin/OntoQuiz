import "reflect-metadata"
import { container, Lifecycle } from "tsyringe";
import { ExerciseController, IExerciseController } from "./controllers/exercise/exercise-controller";
import { TestExerciseController } from "./controllers/exercise/test-exercise-controller";
import { ExerciseStore } from "./stores/exercise-store";
import { QuestionStore } from "./stores/question-store";

const urlParams = new URLSearchParams(window.location.search);
const isTest = urlParams.get('istest') !== null && urlParams.get('istest') !== undefined;

// init DI container
container.register<IExerciseController>("ExerciseController", isTest ? TestExerciseController : ExerciseController);
container.register<QuestionStore>(QuestionStore, QuestionStore);
container.registerSingleton<ExerciseStore>(ExerciseStore);
