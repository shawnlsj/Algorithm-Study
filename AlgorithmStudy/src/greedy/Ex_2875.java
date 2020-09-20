package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ex_2875 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer stn = new StringTokenizer(br.readLine(), " ");
		int female = 0;
		int male = 0;
		int intern = 0;
		int team = 0;

		female = Integer.parseInt(stn.nextToken());
		male = Integer.parseInt(stn.nextToken());
		intern = Integer.parseInt(stn.nextToken());
		System.out.println("female:" + female);
		System.out.println("male:" + male);
		System.out.println("intern:" + intern);
		// 여자가 남자와 같거나 더 많을 경우 남자 기준으로 팀을 작성
		while (true) {
			if (female >= male * 2) {
				team = male;
				System.out.println("team:" + team);
				if (intern > 0) {
					female--;
					intern--;
				}
				if (intern <= 0 || team == 0 || female <= 0) {
					team = getTeam(female, male);
					break;
				}
				boolean isRemain = (team == getTeam(female, male)) ? true : false;
				// 팀이 유지 되었다면, 여자를 또 빼고
				if (isRemain) {
					if(intern<=0) break;
					female--;
					intern--;
					if (team != getTeam(female, male)) {
						female++;
						male--;
						if (team != getTeam(female, male)) {
							team--;
						}
					}
				} else { // 팀이 깨졌다면, 남자를 뺀다
					team--;
					male--;
					intern--;
				}

			} else { // 팀을짜면 남자가 남는 경우로 남자를 뺀다
				team = female / 2;
				if(intern>0) {
				male--;
				intern--;
				}
				boolean isRemain = (team == getTeam(female, male)) ? true : false;

				if (isRemain) {
					if(intern<=0) break;
					male--;
					intern--;
					if (team != getTeam(female, male)) {
						male++;
						female--;
						if (team != getTeam(female, male)) {
							team--;
						}
					}
				} else {
					team--;
					female--;
					intern--;
				}

				if (intern <= 0 || team == 0 || male <= 0) {
					team = getTeam(female, male);
					break;
				}

			} 
		} 

		System.out.print(team);
	}// main

	public static int getTeam(int female, int male) {
		if (female >= male * 2) {
			return male;
		} else {
			return female / 2;
		}
	}
}// class
