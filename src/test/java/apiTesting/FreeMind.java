package apiTesting;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import dataProvider.ConfigFileReader;
import dataProvider.FileReaderManager;
import mainStream.BaseTest;

//@RunWith(MyTestRunner.class)
public class FreeMind extends BaseTest {

	int solution(int[] A) {
		int N = A.length;
		int result = 0;
        for (int i = 0; i < N; i++)
			for (int j = N - 1; j > 0; j--) {
				if (A[i] == A[j]) {
					return Math.max(result, Math.abs(i - j));					
				}
			}
        return result;
	}
    // ([1, 2, 3, 4], 4, 6)
	public int[] solution(int[] A, int F, int M) {
		int[] resultEmpty = { 0 };
		int totalSum = Arrays.stream(A).sum();
		int sumResult = M*(A.length +F) - totalSum;
		
		if (sumResult >F*6 || sumResult<0)
			return resultEmpty;
		
		int [] result= new int[F];
		do {
			for(int i=0; i<result.length; i++)
			{
				if(sumResult>0)
				{
					result[i]+=1;
					sumResult--;
				}
				else
					break;
			}
		}
		while(sumResult>0);
			
		return result;
	}
    
	@Test
	public void T04_ExceptionTest() {
		throw new RuntimeException();
	}

}
