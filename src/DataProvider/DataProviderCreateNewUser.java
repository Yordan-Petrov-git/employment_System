package DataProvider;

import Models.User;

public class DataProviderCreateNewUser {

    public void initializeNewUser(long id, String username,String password,String salt
            , String passPhrase,String firstName,String familyName,String phoneNumber
            ,String emailAddress,String accountType, String picture){

        User user = new User(id,username,password,salt,passPhrase,firstName,familyName,phoneNumber
                ,emailAddress,accountType,picture);
    }

}
