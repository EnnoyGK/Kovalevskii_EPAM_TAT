package by.kovalevskii.framework.service;

import by.kovalevskii.framework.model.User;
import by.kovalevskii.framework.util.StringUtil;

public class UserCreator {
    public static final String TESTDATA_USER_EMAIL = "testdata.user.email";
    public static final String TESTDATA_USER_PWD = "testdata.user.password";
    public static final String TESTDATA_USER_FNAME = "testdata.user.firstName";
    public static final String TESTDATA_USER_LNAME = "testdata.user.lastName";
    public static final String TESTDATA_USER_DOB = "testdata.user.dateOfBirth";

    public static User withCredentialsFromProperty() {
        return new User(TestDataReader.getTestData(TESTDATA_USER_EMAIL),
                TestDataReader.getTestData(TESTDATA_USER_PWD),
                TestDataReader.getTestData(TESTDATA_USER_FNAME),
                TestDataReader.getTestData(TESTDATA_USER_LNAME),
                TestDataReader.getTestData(TESTDATA_USER_DOB));
    }

    public static User withRandomCredentials(){
        return new User(StringUtil.randomEmail(),
                StringUtil.randomString(9),
                StringUtil.randomString(6),
                StringUtil.randomString(8),
                TestDataReader.getTestData(TESTDATA_USER_DOB));
    }
}
