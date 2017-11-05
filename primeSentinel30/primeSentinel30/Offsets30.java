package primeSentinel30;

public class Offsets30 {

	private int[] offsets = new int[8];

	/** x - number with ending 1,7,11,13,17,19,23,29
	 *  row - unity
	*/
	public int[] calculateOffsets(int x, int row) {
	
		if(row == 7) {	/*	29	*/

			offsets[0] = (x*29)/30;
			offsets[1] = (x*23)/30;
			offsets[2] = (x*19)/30;
			offsets[3] = (x*17)/30;
			offsets[4] = (x*13)/30;
			offsets[5] = (x*11)/30;
			offsets[6] = (x*7)/30;
			offsets[7] = (x*31)/30;
		}
		else if(row == 6) {	/*	23	*/

			offsets[0] = (x*17)/30;
			offsets[1] = (x*29)/30;
			offsets[2] = (x*7)/30;
			offsets[3] = (x*11)/30;
			offsets[4] = (x*19)/30;
			offsets[5] = (x*23)/30;
			offsets[6] = (x*31)/30;
			offsets[7] = (x*13)/30;
		}
		else if(row == 5) {	/*	19	*/

			offsets[0] = (x*19)/30;
			offsets[1] = (x*13)/30;
			offsets[2] = (x*29)/30;
			offsets[3] = (x*7)/30;
			offsets[4] = (x*23)/30;
			offsets[5] = (x*31)/30;
			offsets[6] = (x*17)/30;
			offsets[7] = (x*11)/30;
		}
		else if(row == 4) {	/*	17	*/

			offsets[0] = (x*23)/30;
			offsets[1] = (x*11)/30;
			offsets[2] = (x*13)/30;
			offsets[3] = (x*29)/30;
			offsets[4] = (x*31)/30;
			offsets[5] = (x*17)/30;
			offsets[6] = (x*19)/30;
			offsets[7] = (x*7)/30;
		}
		else if(row == 3) {	/*	13	*/

			offsets[0] = (x*7)/30;
			offsets[1] = (x*19)/30;
			offsets[2] = (x*17)/30;
			offsets[3] = (x*31)/30;
			offsets[4] = (x*29)/30;
			offsets[5] = (x*13)/30;
			offsets[6] = (x*11)/30;
			offsets[7] = (x*23)/30;
		}
		else if(row == 2) {	/*	11	*/

			offsets[0] = (x*11)/30;
			offsets[1] = (x*17)/30;
			offsets[2] = (x*31)/30;
			offsets[3] = (x*23)/30;
			offsets[4] = (x*7)/30;
			offsets[5] = (x*29)/30;
			offsets[6] = (x*13)/30;
			offsets[7] = (x*19)/30;
		}
		else if(row == 1) {	/*	7	*/

			offsets[0] = (x*13)/30;
			offsets[1] = (x*31)/30;
			offsets[2] = (x*23)/30;
			offsets[3] = (x*19)/30;
			offsets[4] = (x*11)/30;
			offsets[5] = (x*7)/30;
			offsets[6] = (x*29)/30;
			offsets[7] = (x*17)/30;
		}
		else if(row == 0) {	/*	31	*/

			offsets[0] = (x*31)/30;
			offsets[1] = (x*7)/30;
			offsets[2] = (x*11)/30;
			offsets[3] = (x*13)/30;
			offsets[4] = (x*17)/30;
			offsets[5] = (x*19)/30;
			offsets[6] = (x*23)/30;
			offsets[7] = (x*29)/30;
		}
		else {
			System.out.println("something went wrong in the calculateOffsets switch instruction");
		}

		int xPowMod = ( (int) Math.pow(x, 2) ) / 30;
		while(offsets[0] < xPowMod) offsets[0] += x;
		while(offsets[1] < xPowMod) offsets[1] += x;
		while(offsets[2] < xPowMod) offsets[2] += x;
		while(offsets[3] < xPowMod) offsets[3] += x;
		while(offsets[4] < xPowMod) offsets[4] += x;
		while(offsets[5] < xPowMod) offsets[5] += x;
		while(offsets[6] < xPowMod) offsets[6] += x;
		while(offsets[7] < xPowMod) offsets[7] += x;

		return offsets;
	}
}



