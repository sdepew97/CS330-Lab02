//import statements
import java.util.Random;

public class Lab02
{
  //how to compile: java -Xss2000M Lab02
  
  //global variables and arrays
  public static int arrayALength = 50000000;
  public static int[] A = new int[arrayALength];
  public static String[] algorithmNames = {"linearSearch", "betterLinearSearch", "sentinelLinearSearch", "recursiveLinearSearch"};

  //main function
  public static void main(String args[])
  {
    long bestStart;
    double duration;

    //initalize and shuffle
    initalizeA(A, arrayALength);
    fisherYatesShuffle(A, arrayALength);

    //best case
    int bestCase = A[0];

    bestStart = System.nanoTime();
    int linearSearchBest = linearSearch(A, arrayALength, bestCase);
    duration = (System.nanoTime() - bestStart)/1000000.0; //duration is in miliseconds
    printInformation(arrayALength, algorithmNames[0], bestCase, linearSearchBest, duration);

    bestStart = System.nanoTime();
    int betterLinearSearchBest = betterLinearSearch(A, arrayALength, bestCase);
    duration = (System.nanoTime() - bestStart)/1000000.0; //duration is in miliseconds
    printInformation(arrayALength, algorithmNames[1], bestCase, betterLinearSearchBest, duration);

    bestStart = System.nanoTime();
    int sentinelLinearSearchBest = sentinelLinearSearch(A, arrayALength, bestCase);
    duration = (System.nanoTime() - bestStart)/1000000.0; //duration is in miliseconds
    printInformation(arrayALength, algorithmNames[2], bestCase, sentinelLinearSearchBest, duration);

    bestStart = System.nanoTime();
    int recursiveLinearSearchBest = recursiveLinearSearch(A, arrayALength, 0, bestCase);
    duration = (System.nanoTime() - bestStart)/1000000.0; //duration is in miliseconds
    printInformation(arrayALength, algorithmNames[3], bestCase, recursiveLinearSearchBest, duration);

    //average case
    //get random integer
    Random random = new Random();
    int averageCase = random.nextInt(arrayALength*4);

    bestStart = System.nanoTime();
    int linearSearchAverage = linearSearch(A, arrayALength, averageCase);
    duration = (System.nanoTime() - bestStart)/1000000.0; //duration is in miliseconds
    printInformation(arrayALength, algorithmNames[0], averageCase, linearSearchAverage, duration);

    bestStart = System.nanoTime();
    int betterLinearSearchAverage = betterLinearSearch(A, arrayALength, averageCase);
    duration = (System.nanoTime() - bestStart)/1000000.0; //duration is in miliseconds
    printInformation(arrayALength, algorithmNames[1], averageCase, betterLinearSearchAverage, duration);

    bestStart = System.nanoTime();
    int sentinelLinearSearchAverage = sentinelLinearSearch(A, arrayALength, averageCase);
    duration = (System.nanoTime() - bestStart)/1000000.0; //duration is in miliseconds
    printInformation(arrayALength, algorithmNames[2], averageCase, sentinelLinearSearchAverage, duration);

    bestStart = System.nanoTime();
    int recursiveLinearSearchAverage = recursiveLinearSearch(A, arrayALength, 0, averageCase);
    duration = (System.nanoTime() - bestStart)/1000000.0; //duration is in miliseconds
    printInformation(arrayALength, algorithmNames[3], averageCase, recursiveLinearSearchAverage, duration);

    //worst case
    int worstCase = -10;

    bestStart = System.nanoTime();
    int linearSearchWorst = linearSearch(A, arrayALength, worstCase);
    duration = (System.nanoTime() - bestStart)/1000000.0; //duration is in miliseconds
    printInformation(arrayALength, algorithmNames[0], worstCase, linearSearchWorst, duration);

    bestStart = System.nanoTime();
    int betterLinearSearchWorst = betterLinearSearch(A, arrayALength, worstCase);
    duration = (System.nanoTime() - bestStart)/1000000.0; //duration is in miliseconds
    printInformation(arrayALength, algorithmNames[1], worstCase, betterLinearSearchWorst, duration);

    bestStart = System.nanoTime();
    int sentinelLinearSearchWorst = sentinelLinearSearch(A, arrayALength, worstCase);
    duration = (System.nanoTime() - bestStart)/1000000.0; //duration is in miliseconds
    printInformation(arrayALength, algorithmNames[2], worstCase, sentinelLinearSearchWorst, duration);

    bestStart = System.nanoTime();
    int recursiveLinearSearchWorst = recursiveLinearSearch(A, arrayALength, 0, worstCase);
    duration = (System.nanoTime() - bestStart)/1000000.0; //duration is in miliseconds
    printInformation(arrayALength, algorithmNames[3], worstCase, recursiveLinearSearchWorst, duration);
}

  public static int linearSearch(int A[], int n, int x)
  {
    int answer = -1;

    for(int i=0; i<n; i++)
    {
      if(A[i]==x)
      {
        answer = i;
      }
    }
    return answer;
  }

  public static int betterLinearSearch(int A[], int n, int x)
  {
    for(int i=0; i<n; i++)
    {
      if(A[i]==x)
      {
        return i;
      }
    }
    return -1;
  }

  public static int sentinelLinearSearch(int A[], int n, int x)
  {
    int last = A[n-1];
    A[n-1] = x;
    int i=0;

    while(A[i]!=x)
    {
      i=i+1;
    }
    A[n-1] = last;
    if(i<n-1 || A[n-1] == x)
    {
      return i;
    }
    return -1;
  }

  public static int recursiveLinearSearch(int A[], int n, int i, int x)
  {
    if(i>=n)
    {
      return -1;
    }
    else if(A[i] == x)
    {
      return i;
    }
    else
    {
      return recursiveLinearSearch(A, n, i+1, x);
    }
  }

  //method for initalizing the array A with random values between 1 and 50 million
  public static void initalizeA(int[] A, int n)
  {
    for(int i=0; i<n; i++)
    {
      A[i] = i;
    }
  }

  //method for shuffling the array A[]
  public static void fisherYatesShuffle(int[] A, int n)
  {
    Random random = new Random();
    for(int i=0; i<n; i++)
    {
      int j = random.nextInt(n); //get a random int between 0 and n exclusive of n
      int temporary = A[i];
      A[i] = A[j];
      A[j] = temporary;
    }
  }

  public static void printInformation(int n, String algorithm, int x, int result, double duration)
  {
    System.out.println("Searching array of size: " + n);
    System.out.println("Algorithm: " + algorithm);
    System.out.println("Looking for element: " + x);
    System.out.println("Result: " + result);
    System.out.println("Duration: " + duration);
    System.out.println();
  }
} //Lab02
