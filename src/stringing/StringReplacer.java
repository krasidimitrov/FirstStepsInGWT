package stringing;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */
public class StringReplacer {

  public String rep(String text){
    String concatenatedText ="";
//    String[] splitText = text.split("<u>|</u>|<l>|</l>|<fu>|</fu>");
    String[] splitText = text.split("<|>|</");
    for(int i=1; i<splitText.length; i=i+2){
//      splitText[i]=splitText[i].toUpperCase();
      if(splitText[i].equals("u")){
        splitText[i+1]=splitText[i+1].toUpperCase();
      }
      if(splitText[i].equals("l")){
        splitText[i+1]=splitText[i+1].toLowerCase();
      }
      if(splitText[i].equals("fu")){
        splitText[i+1]=splitText[i+1].substring(0,1).toUpperCase() + splitText[i+1].substring(1,splitText[i+1].length()).toLowerCase();
      }
    }

    for(int i=0; i<splitText.length; i=i+2){
      concatenatedText = concatenatedText+splitText[i];
    }

    return concatenatedText;
  }
  
  
  public static void main(String[] args) {
    StringReplacer stringReplacer = new StringReplacer();
    String text = "Hi <fu>iVaNE</fu>. <u>How are you</u>? Do you <l>hEaR ME</l>?";
    System.out.println(stringReplacer.rep(text));
  }
}
