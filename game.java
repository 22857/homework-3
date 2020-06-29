package pokeruser;
import java.util.Scanner;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class UserTest {
		public static void main(String[] args) throws Exception {
			String str_First;
			String str_Second;
			try (Scanner sc = new Scanner(System.in)) {
				str_First = sc.next();
				str_Second = sc.next();
			}
			Board b=new Board();
		boolean flag=b.compareCards(str_First,str_Second);
		if(flag) {
			System.out.println("Black Wins");
		}else {
			System.out.println("White Wins");
		}
	}
}
class Board{
	public String getCardArr(final char s) throws Exception {
		String str="";
			switch(s) {
			case 'A':
				str="A";
				break;
			case '2':
				str="2";
				break;
			case '3':
				str="3";
				break;
			case '4':
				str="4";
				break;
			case '5':
				str="5";
				break;
			case '6':
				str="6";
				break;
			case '7':
				str="7";
				break;
			case '8':
				str="8";
				break;
			case '9':
				str="9";
				break;
			case 'T':
				str="T";
				break;
			case 'J':
				str="J";
				break;
			case 'Q':
				str="Q";
				break;
			case 'K':
				str="K";
				break;
			default:
				throw new  Exception("Null");
			}
		return str;
	}
	public int getCardSign(final String cards) throws Exception {
		String s="";
		String s1="";
		for(int i=0;i<cards.length();i+=2) {
			s1+=cards.charAt(i)+"";
		}
		for(int i=1;i<cards.length();i+=2) {
			s+=cards.charAt(i)+"";
		}
		return getCardslevel(s,s1);
	}

	public int getCardslevel(final String s,final String s1) {
		int count_s=0;
		int level = 0;
		for(int i=0;i<s.length();i++) {
			for(int j=i+1;j<s.length();j++) {
				if((s.charAt(i)+"").equals((s.charAt(j)+""))){
					count_s++;
				}
			}
		}
		switch(count_s) {
		case 1:
			level=1;
			break;
		case 2:
			level=2;
			break;
		case 3:
			level=3;
			break;
		case 4:
			level=6;
			break;
		case 6:
			level=7;
			break;
		default:
			level=getCardsColor(s,s1);
		}
		return level;
	}
	public int getCardsColor(final String s,final String s1) {
		int count_s1=0;
		int level=0;
		for(int i=0;i<s1.length();i++) {
			for(int j=i+1;j<s1.length();j++) {
				if((s1.charAt(i)+"").equals((s1.charAt(j)+""))){
					count_s1++;
				}
			}
		}
		switch(count_s1) {
		case 10:
			level=5;
			if(transition(s)==true) {
				level=8;
			}else {
				System.out.println();
			}
			break;
		default:
			if(transition(s)==true) {
				level=4;
			}
		}
		return level;
	}
	public boolean transition(final String s) {
		final char[] a=s.toCharArray();
		final int[] b=new int[a.length];
		for(int j=0;j<a.length;j++) {
			if (Character.isDigit(a[j])){
			    b[j]= Integer.parseInt(String.valueOf(a[j]));
			} else if(a[j]=='T') {
				b[j]=10;
			}else if(a[j]=='J') {
				b[j]=11;
			}else if(a[j]=='Q') {
				b[j]=12;
			}else if(a[j]=='K') {
				b[j]=13;
			}else if(a[j]=='A') {
				b[j]=1;
			}
		}
		return isNStraightHand(b, b.length);
	}
	public boolean isNStraightHand(final int[] b, final int length) {
        if (b == null || b.length == 0 || b.length % length != 0) {
            return false;
        }
        Arrays.sort(b);
        final Map<Integer, Integer> map = new HashMap<>();
        for (final int i : b) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        for (final int h : b) {
            if (map.get(h) > 0) {
                for (int j = 0; j < length; j++) {
                    if (map.getOrDefault(h + j, 0) > 0) {
                        map.put(h + j, map.get(h + j) - 1);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
	public boolean ColorBord(final String cardsFirst, final String cardsSecond) {
		String s_first="";
		String s1_Second="";
		for(int i=0;i<cardsFirst.length();i+=2) {
			s_first+=cardsFirst.charAt(i)+"";
		}
		for(int i=0;i<cardsSecond.length();i+=2) {
			s1_Second+=cardsSecond.charAt(i)+"";
		}
		//System.out.println(s_first+"=="+s1_Second);
		final char[] a=s_first.toCharArray();
		final int[] b=new int[a.length];
		for(int j=0;j<a.length;j++) {
			if(a[j]=='B') {
				b[j]=4;
			}else if(a[j]=='R') {
				b[j]=3;
			}else if(a[j]=='M') {
				b[j]=2;
			}else if(a[j]=='F') {
				b[j]=1;
			}
			System.out.print(b[j]);
		}
		System.out.println();
		final char[] a1=s1_Second.toCharArray();
		final int[] b1=new int[a1.length];
		for(int j=0;j<a1.length;j++) {
			if(a1[j]=='B') {
				b1[j]=4;
			}else if(a1[j]=='R') {
				b1[j]=3;
			}else if(a1[j]=='M') {
				b1[j]=2;
			}else if(a1[j]=='F') {
				b1[j]=1;
			}
			System.out.print(b1[j]);
		}
		System.out.println();
		int count=1;
		int count1=1;
		int n=0;
		Arrays.sort(b);
		Arrays.sort(b1);
		for(int i=0;i<b.length-1;i++) {
			if(b[i]==b[i+1]) {
				count++;
				n=i;
			}
		}
		for(int i=0;i<b1.length-1;i++) {
			if(b1[i]==b1[i+1]) {
				count1++;
			}
		}	
		if(count<count1) {
			return false;
		}else if(count==count1) {
			if(b[n]<b1[n]) {
				return false; 
			}else if(b[n]==b1[n]) {
				return ValueBord(cardsFirst,cardsSecond);
			}
		}
		return true;
	}
	public boolean ValueBord(final String cardsFirst,final String cardsSecond) {
		String s_first="";
		String s1_Second="";
		for(int i=1;i<cardsFirst.length();i+=2) {
			s_first+=cardsFirst.charAt(i)+"";
		}
		for(int i=1;i<cardsSecond.length();i+=2) {
			s1_Second+=cardsSecond.charAt(i)+"";
		}
		final char[] a=s_first.toCharArray();
		final int[] b=new int[a.length];
		for(int j=0;j<a.length;j++) {
			if (Character.isDigit(a[j])){
			    b[j]= Integer.parseInt(String.valueOf(a[j]));
			} else if(a[j]=='T') {
				b[j]=10;
			}else if(a[j]=='J') {
				b[j]=11;
			}else if(a[j]=='Q') {
				b[j]=12;
			}else if(a[j]=='K') {
				b[j]=13;
			}else if(a[j]=='A') {
				b[j]=1;
			}
		}
		final char[] a1=s1_Second.toCharArray();
		final int[] b1=new int[a1.length];
		for(int j=0;j<a1.length;j++) {
			if (Character.isDigit(a1[j])){
			    b1[j]= Integer.parseInt(String.valueOf(a1[j]));
			} else if(a1[j]=='T') {
				b1[j]=10;
			}else if(a1[j]=='J') {
				b1[j]=11;
			}else if(a1[j]=='Q') {
				b1[j]=12;
			}else if(a1[j]=='K') {
				b1[j]=13;
			}else if(a1[j]=='A') {
				b1[j]=1;
			}
		}
		int s=0;
		int s1=0;
		for(int i=0;i<b.length;i++) {
			s+=b[i];
		}
		for(int i=0;i<b1.length;i++) {
			s1+=b1[i];
		}
		if(s<s1) {
			return false;
		}
		return true;
	}
	public void overCards(final String cardsFirst, final String cardsSecond) throws Exception {
		String s_first="";
		String s1_first="";
		String s_Second="";
		String s1_Second="";	
		for(int i=0;i<cardsFirst.length();i+=2) {
			s_first+=cardsFirst.charAt(i)+"";
		}
		for(int i=1;i<cardsFirst.length();i+=2) {
			s1_first+=cardsFirst.charAt(i)+"";
		}
		for(int i=0;i<cardsSecond.length();i+=2) {
			s_Second+=cardsSecond.charAt(i)+"";
		}
		for(int i=1;i<cardsSecond.length();i+=2) {
			s1_Second+=cardsSecond.charAt(i)+"";
		}
		for(int i=0;i<s1_first.length();i++) {
			for(int k=0;k<s1_Second.length();k++) {
					if((s1_first.charAt(i)+"").equals(s1_Second.charAt(k)+"")) {
							if((s_first.charAt(i)+"").equals(s_Second.charAt(k)+"")) {
								throw new  Exception("牌数不合法");
							}
					}
			}
		}
	}
	public boolean compareCards(final String cardsFirst, final String cardsSecond) throws Exception {
		overCards(cardsFirst,cardsSecond);
		boolean flag=false;
			final int f=getCardSign(cardsFirst);
			final int s=getCardSign(cardsSecond);
			if(f>s) {
				flag=true;
			}else if(f==s) {
				if(cardsFirst.equals(cardsSecond)) {
					System.out.println("Tie");
				}else {
						return ColorBord(cardsFirst,cardsSecond);
				}
			}
		return flag;
	}
}
