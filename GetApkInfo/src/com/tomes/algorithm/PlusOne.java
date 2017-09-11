package com.tomes.algorithm;

/**给一个数组的末位+1
 * @author Toems
 *
 */
public class PlusOne {

	public static void main(String[] args) {
		int[] digits={0};
		int[] res = plusOne(digits);
		for (int i : res) {
			System.out.println(i);
		}
		
	}
    public static int[] plusOne1(int[] digits) {
        if(digits == null || digits.length==0)  
            return digits;  
        int carry = 1;  //388
        for(int i=digits.length-1;i>=0;i--)  
        {  
            int digit = (digits[i]+carry)%10;   
            carry = (digits[i]+carry)/10;  
            digits[i] = digit;  
            if(carry==0)  
                return digits;      
        }  
        int [] res = new int[digits.length+1];      
        res[0] = 1;  
        return res;  
    }
    

    /**给数组的末尾数+1，然后返回一个新的数组
     * @param digits 输入的数组
     * @return
     */
    private static int[] plusOne(int[] digits) {
    	if(digits==null||digits.length==0){
    		return digits;
    	}
    	for (int i = digits.length-1; i >=0; i--) {
			if(digits[i]<9){
				digits[i]+=1;
				return digits;
			}
			digits[i]=0;
		}
    	int[] res=new int[digits.length+1];
    	res[0]=1;
		return res;
	}
}
