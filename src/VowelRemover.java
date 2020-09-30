public class VowelRemover {
    public String disemvowelizer(String input){
        StringBuilder output = new StringBuilder();
        for(int i = 0; i < input.length(); i++){
            if(!this.isVowel(input.charAt(i))){
                //If not a vowel, add to the output
                output.append(input.charAt(i));
            }
        }
        return output.toString();
    }

    public boolean isVowel(char input){
        char[] vowels = new char[]{'a','e','i','o','u','A','E','I','O','U'}; //All vowels
        for(int i = 0; i < vowels.length; i++){
            if (input == vowels[i]){
                return true;
            }
        }
        return false;
    }
}
