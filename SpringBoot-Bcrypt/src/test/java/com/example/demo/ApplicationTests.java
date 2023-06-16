package com.example.demo;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BcryptTests {

    /**
     * 加密
     */
    @Test
    void encodedPassword() {
        String password = "123456";

        // 加密
        String encodedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(encodedPassword);
        // $2a$10$iKmJO4uwlq09BKyVifxJJ.U46L5Xc3mv8aBW8FKrRVWNoEqpsoYN6
    }

    /**
     * 验证正确密码
     */
    @Test
    void checkTruePassword() {
        String password = "123456";
        String encodedPassword = "$2a$10$iKmJO4uwlq09BKyVifxJJ.U46L5Xc3mv8aBW8FKrRVWNoEqpsoYN6";

        boolean flag = BCrypt.checkpw(password, encodedPassword);
        System.out.println(flag);
        // true
    }

    /**
     * 验证错误密码
     */
    @Test
    void checkFalsePassword() {
        String password = "111222";
        String encodedPassword = "$2a$10$iKmJO4uwlq09BKyVifxJJ.U46L5Xc3mv8aBW8FKrRVWNoEqpsoYN6";

        boolean flag = BCrypt.checkpw(password, encodedPassword);
        System.out.println(flag);
        // false
    }
}
