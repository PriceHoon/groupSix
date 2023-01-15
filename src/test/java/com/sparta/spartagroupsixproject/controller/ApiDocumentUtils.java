package com.sparta.spartagroupsixproject.controller;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

public interface ApiDocumentUtils {

    static OperationRequestPreprocessor getDocumentRequest() {
        return preprocessRequest(
                prettyPrint()
        );
    }
    static OperationResponsePreprocessor getDocumentResponsee(){
        return preprocessResponse(prettyPrint());
    }
}
