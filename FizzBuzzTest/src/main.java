

public class main {
	public static void main(String[] args)
	{
		for(int i = 1; i <= 100; i++)
		{   
			//Checks to see if i is multiple of 3 and 5
			if(i%3 == 0 && i%5 == 0){
				System.out.println("FizzBuzz");
			}
			//multiple of 3
			else if(i%3 == 0)
			{
				System.out.println("Fizz");
			}
			//multiple of 5
			else if(i%5 == 0)
			{
				System.out.println("Buzz");
			}
			//If not multiple of 3 or 5
			else
			{
				System.out.println(i);
			}

		}
	}

}
