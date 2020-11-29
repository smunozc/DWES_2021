package misc;

import java.util.regex.Pattern;

public class RegexValidator {
	
	private final String NAME_REGEX =
            "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
 
    private final Pattern NAME_PATTERN = Pattern.compile(NAME_REGEX);
 
    // alphanumeric and underscore are allowed
    private final String USERNAME_REGEX = "^[a-zA-Z0-9_]+$";
 
    private final Pattern USERNAME_PATTERN =
                                        Pattern.compile(USERNAME_REGEX);
 
    // US phone number with or without dashes
    private final String PHONE_REGEX =
            "^\\D?(\\d{3})\\D?\\D?(\\d{3})\\D?(\\d{4})$";
 
    private final Pattern PHONE_PATTERN = Pattern.compile(PHONE_REGEX);
 
    // local-part + @ + domain part
    private final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*" +
            "@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
 
    private final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);
 
    // 8-16 characters password with at least one digit, one lowercase letter,
    // one uppercase letter, one special character with no whitespaces
    private final String PASSWORD_REGEX =
        "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,16}$";
 
    private final Pattern PASSWORD_PATTERN =
            Pattern.compile(PASSWORD_REGEX);
 
    // Date in US format with support for leap years
    private final String DATE_REGEX =
            "^(?:(?:(?:0?[13578]|1[02])(\\/|-|\\.)31)\\1|" +
            "(?:(?:0?[1,3-9]|1[0-2])(\\/|-|\\.)(?:29|30)\\2))" +
            "(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$|^(?:0?2(\\/|-|\\.)29\\3" +
            "(?:(?:(?:1[6-9]|[2-9]\\d)?(?:0[48]|[2468][048]|" +
            "[13579][26])|(?:(?:16|[2468][048]|[3579][26])00))))$|" +
            "^(?:(?:0?[1-9])|(?:1[0-2]))(\\/|-|\\.)(?:0?[1-9]|1\\d|" +
            "2[0-8])\\4(?:(?:1[6-9]|[2-9]\\d)?\\d{2})$";
 
    private final Pattern DATE_PATTERN = Pattern.compile(DATE_REGEX);
 
    // A valid credit card number
    private final String CREDIT_CARD_REGEX =
            "^((4\\d{3})|(5[1-5]\\d{2})|(6011)|(7\\d{3}))" +
            "-?\\d{4}-?\\d{4}-?\\d{4}|3[4,7]\\d{13}$";
 
    private final Pattern CREDIT_CARD_PATTERN =
                                        Pattern.compile(CREDIT_CARD_REGEX);

    public boolean checkUsername(String username) {
    	return USERNAME_PATTERN.matcher(username).matches();
    }
    
    public boolean checkName(String name) {
    	return NAME_PATTERN.matcher(name).matches();
    }
    
    public boolean checkPhone(String phone) {
    	return PHONE_PATTERN.matcher(phone).matches();
    }
    
    public boolean checkEmail(String email) {
    	return EMAIL_PATTERN.matcher(email).matches();
    }
    
    public boolean checkPassword(String password) {
    	return PASSWORD_PATTERN.matcher(password).matches();
    }
    
    public boolean checkDate(String date) {
    	return DATE_PATTERN.matcher(date).matches();
    }
    
    public boolean checkCreditCard(String creditCardNumber) {
    	return CREDIT_CARD_PATTERN.matcher(creditCardNumber).matches();
    }
    
}
