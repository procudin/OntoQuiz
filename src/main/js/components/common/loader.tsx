import React, { useEffect, useState } from "react";
import { Spinner } from "react-bootstrap";

type LoaderProps = {
    delay?: number,
}
export const Loader = (props: LoaderProps) => {
    const delay = props.delay ?? 0;
    const [enabled, setEnabled] = useState(delay === 0);
    useEffect(() => {
        if (delay > 0) {
            setTimeout(() => !enabled && setEnabled(true), delay);
        }
    });

    if (!enabled) {
        return null;
    }
    return <Spinner animation="border" variant="primary" />
};

type LoadingWrapperProps = LoaderProps & {
    isLoading: boolean,
    children?: React.ReactNode[] | React.ReactNode,
}
export const LoadingWrapper = (props: LoadingWrapperProps) => {
    const { children, isLoading } = props;
    if (isLoading) {
        return <Loader {...props} />;
    }
    return <>{children}</>;
};
