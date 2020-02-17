package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail extends Authenticator {

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        //아이디, 패스워드입력
        return new PasswordAuthentication("계정아이디", "패스워드");

    }
}
