package io.github.edgardobarriam.springgcpchallenge.util;

public interface RUTUtils {
  
  static boolean isValid(String rut) {
    String nonformattedRut = cleanRut(rut);
    
    if (nonformattedRut.length() > 9) { return false; }
    
    String dv = nonformattedRut.substring(nonformattedRut.length()-1);
    String calcDv = calcDV(nonformattedRut);
    
    return calcDv.equals(dv);
  }
  
  static String cleanRut(String rut) {
    return rut.replaceAll("[ .-]+",""); // Removes spaces, dots and dashes
  }
  
  static String calcDV(String rut) {
    String rutWithoutDV = rut.substring(0,rut.length()-1);
    
    int[] validationSequence = {2,3,4,5,6,7,2,3};
    
    int sum = 0;
    
    for (int i = rutWithoutDV.length(); i>0; i--) {
      int digit = Integer.parseInt(rutWithoutDV.substring(i-1,i));
      sum += digit * validationSequence[rutWithoutDV.length() - i];
    }
    
    int rem = 11 - (sum%11);
    return rem == 10 ? "K" : rem == 11 ? "0" : String.valueOf(rem);
  }
}
