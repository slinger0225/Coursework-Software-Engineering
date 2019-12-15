/**
 * Check user's or administrator's input are in a correct form.
 * @author Group 44
 * @version 1.2
 */
public class CheckInput {
	// Check whether the input is all word.

	/**
	 * If the input text is a word.
	 * @param nameText The input string
	 * @return true when the form is correct, false when not.
	 */
	protected boolean isAllWords(String nameText) {
		char[] array = nameText.toCharArray();
		for (Character ch : array) {
			if (ch < 'A' || ch > 'z' || (ch < 'a' && ch > 'Z')) {
				return true;
			} else {
				return false;
			}
		}
		return true;
	}

	// Check whether the input is all number.
	/**
	 * If the input text is a number.
	 * @param idText The input string
	 * @return true when the form is correct, false when not.
	 */
	protected boolean isNumeric(String idText) {
		for (int i = 0; i < idText.length(); i++) {
			if (!Character.isDigit(idText.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	// Check whether the input is a valid Email address.
	/**
	 * If the input text is a E-mail address.
	 * @param idText The input string
	 * @return true when the form is correct, false when not.
	 */
	protected boolean validateEmail(String idText) {
		if (idText.indexOf("@") == -1 || idText.indexOf("@") == 0 || idText.indexOf("@") == idText.length() - 1) {
			return true;
		} else if (idText.indexOf(".") == -1 || idText.indexOf(".") == 0
				|| idText.indexOf(".") == idText.length() - 1) {
			return true;
		} else if (idText.length() < 2) {
			return true;
		}
		return false;
	}

}
