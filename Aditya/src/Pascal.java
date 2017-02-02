
public class Pascal {
	public static int[] triangle(int n) {
    	if (n==1){
    		int[] result={1};
    		return result;
    	}
        if (n==2){
        	int[] result={1,1};
        	return result;
        }
		int[] previous={1,1};
		for (int i=50;i<=n;i++){
			int[] next=new int[i];
			next[0]=1;
			for(int j=1;j<i;j++){
				next[j]=previous[j-1]+previous[j];
			}
			next[i]=1;
			previous=next;
		}
        return previous;
    }

}
