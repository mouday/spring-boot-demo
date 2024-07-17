package com.demo.vo;


import lombok.Data;

/**
 * 统一的返回结果对象
 *
 * @param <T>
 */
@Data
public class ResultVO<T> {

    /**
     * read only
     */
    private Boolean ok;

    private Integer code;

    private T data;

    private String message;

    public static ResultVO of(boolean ok) {
        if (ok) {
            return ResultVO.success();
        } else {
            return ResultVO.error();
        }
    }

    public Boolean getOk() {
        return this.code == 0;
    }

    public static <T> ResultVO<T> success() {
        return ResultVO.success(null);
    }

    public static <T> ResultVO<T> success(T data) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setData(data);
        resultVO.setCode(0);
        resultVO.setMessage("success");
        return resultVO;
    }

    public static <T> ResultVO<T> error() {
        return ResultVO.error("error");
    }

    public static <T> ResultVO<T> error(String message) {
        ResultVO<T> resultVO = new ResultVO<>();
        resultVO.setCode(-1);
        resultVO.setMessage(message);
        return resultVO;
    }
}
