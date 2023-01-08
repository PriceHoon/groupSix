package com.sparta.spartagroupsixproject;

import org.springframework.restdocs.operation.preprocess.OperationRequestPreprocessor;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;

public interface ApiDocumentUtils {

    static OperationRequestPreprocessor getDocumentRequest(){
        return preprocessRequest(
                modifyUris() // (1) - 문서상 URI 값을 변경하기 위해 사용
                        .scheme("http") // http://docs.api.com
                        .host("docs.api.com")
                        .removePort(),
                prettyPrint()); // (2) - request 정리 후 출력
    }
    static OperationResponsePreprocessor getDocumentResponse() {
        return preprocessResponse(prettyPrint()); // (3) - response 출력 위해 사용
    }
}
