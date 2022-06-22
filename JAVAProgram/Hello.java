import java.util.Scanner;
/**
 * @author ��˳ƽ
 * @version 1.0
 */
public class Hello {

	public static void main(String[] args) {
		System.out.println("��˳ƽ����");

/*		Cat myCat = new Cat();

		int[][] arr = new int[8][7];

		for(int i = 0; i < arr[0].length; i++) {
			arr[0][i] = 1;
			arr[arr.length - 1][i] = 1;
		}


		for(int i = 0; i < arr.length; i++) {
			arr[i][0] = 1;
			arr[i][arr[i].length - 1] = 1;
		}

		arr[3][1] = 1;
		arr[3][2] = 1;

		if(myCat.findWay(arr, 1, 1)) {
			System.out.println("�ҵ���");
		}
		for(int i = 0; i < arr.length; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println("-----------------------------------");
		myCat.move(5, 'A', 'B', 'C');*/

		Methods methods = new Methods();
		System.out.println(methods.sum(1,2,3));
	} 

}

class Cat {
	String name;
	int age;

	public void speak() {
		System.out.println("����һ������");
	}

	public void cal02(int n) {
		int res = 0;
		for(int i=1;i<=n;i++) {
			res += i;
		}
		System.out.println(res);
	}

	public int getSum(int num1, int num2) {
		int res = num1 + num2;
		return res;
	}

	public void showArray(int[][] arr) {
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public int[] getArraySum(int num1, int num2) {
		int[] arr = new int[2];
		arr[0] = num1 + num2;
		arr[1] = num1 - num2;

		return arr;
	}

	public boolean findWay(int[][] arr, int x, int y) {
		if(arr[6][5] == 2) {
			return true;
		}else {
			if (arr[x][y] == 0) {
				arr[x][y] = 2;

				if(findWay(arr, x+1, y)) {
					return true;
				}else if(findWay(arr, x, y+1)) {
					return true;
				}else if(findWay(arr, x-1, y)) {
					return true;
				}else if(findWay(arr, x, y-1)) {
					return true;
				}else {
					arr[x][y] = 3; //���������߲�ͨ����3
					return false;
				}
			}else {
				return false;
			}
		}
	}

	public void move(int num, char a, char b, char c) {
		if (num == 1) {
			System.out.println(a + "->" + c);
		}else {
			move(num - 1, a , c, b);
			System.out.println(a + "->" + c);
			move(num - 1,b, a, c);
		}
	}
}

// ��������
class Methods {
	public int m(int n) {
		return n * n;
	}
	public int m(int n1, int n2) {
		return n1 * n2;
	}
	public void m(String s) {
		System.out.println(s);
	}

	public int max(int x, int y) {
		return (x > y ? x : y);
	}
	public double max(double x, double y) {
		return (x > y ? x : y);
	}
	public double max(double x, double y, double z) {
		double tmpMax = (x > y ? x : y);
		return (z > tmpMax ? z : tmpMax);
	}

	public int sum(int... nums) {
		int result = 0;
		for(int i = 0; i < nums.length; i++) {
			result += nums[i];
		}
		return result;
	}
}

class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		//�����û����������
		int n = sc.nextInt();
		int m = sc.nextInt();
		int a[][] = new int[n][n];
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				a[i][j] = sc.nextInt();
			}
		}
		int[][]b = a;//����һ�ݾ���
		
		//�ж�m�Ƿ����0���������0��˵����Ҫ��λ����
		int res[][] = new int[n][n];
		if(m == 0){
			for(int i = 0; i < n; i++){
				for(int j = 0; j < n; j++){
					System.out.print(i==j?"1 ":"0 ");
				}
				System.out.println();
			}
			return;
		}else{
			while(true){
				res = f(a,b);
				a = res;
				
				m--;
				if(m == 1)break;
			}
		}
		
		//��ӡ����
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				System.out.print(res[i][j]+" ");
			}
			System.out.println();
		}
	}
	
	//����֮�����
	static int[][] f(int[][]m1, int[][]m2){
		int n = m1.length;//����m1������
		int m = m1[0].length;//����m1������
		int p = m2[0].length;//����m2������
		
		int[][]res = new int[n][p];
		for(int i = 0; i < n; i++){//��һ�����������
			for(int j = 0; j < m; j++){
				for(int k = 0; k < p; k++){//�ڶ������������
					res[i][j] += m1[i][k] * m2[k][j];
				}
			}
		}
		
		return res;
	}
}