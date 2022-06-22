package top.twip.common.globalconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.twip.common.constant.FeignConstants;
import top.twip.common.enums.CodeEnum;
import top.twip.common.exception.BadRequestDataException;
import top.twip.common.exception.DatabaseDataNotFound;
import top.twip.common.exception.DatabaseHandlerException;
import top.twip.common.response.DataFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 七画一只妖
 * @Date: 2022-06-22 8:46
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    private Object handleException(HttpServletRequest request,Exception e){
        log.error(e.getClass().toString());
        e.printStackTrace();

        // 当本次请求是feign调用，则不进入错误处理逻辑
        if(request.getHeader(FeignConstants.HEADER_NAME.getValue()) != null){
            return null;
        }

        // 错误状态码默认500
        CodeEnum code = CodeEnum.INTERNAL_ERROR;
        if (e instanceof DatabaseDataNotFound){
            code = CodeEnum.NOT_FOUND;
        } else if(e instanceof DatabaseHandlerException){
            code = CodeEnum.BAD_REQUEST;
        } else if(e instanceof BadRequestDataException){
            code = CodeEnum.NOT_ALL_OK;
        }
        return DataFactory.fail(code,e.getMessage());
    }
}
