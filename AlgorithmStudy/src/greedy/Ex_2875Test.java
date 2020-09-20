package greedy;

public class Ex_2875Test {
	public static void main(String[] args) {
		while (true) {
			int a = (int) (Math.random() * 5);
			int b = (int) (Math.random() * 5);
			int c = (int) (Math.random() * (a + b + 1));
			if (answer(a, b, c) != correctAnswer(a, b, c)) {
				System.out.println("a : " + a);
				System.out.println("b : " + b);
				System.out.println("c : " + c);
				System.out.println("no");
				break;
			}
		}
	}

	static int correctAnswer(int n, int m, int k) {

		int max = n / 2 < m ? n / 2 : m;

		k -= n + m - max * 3;

		while (k > 0) {
			k -= 3;
			max--;
		}
		System.out.println("correct : " + max);
		return max;
	}

	static int answer(int n, int m, int k) {
		int female = 0;
		int male = 0;
		int intern = 0;
		int team = 0;

		female = n;
		male = m;
		intern = k;

		while (true) {
			if (female >= male * 2) {
				team = male;
				// if (intern > 0 && female > 0 && male > 0 && team > 0) {
				// 여자를 먼저 뺀 뒤에, 팀이 유지되는지 확인
				if (intern > 0) {
					female--;
					intern--;
				}
				if (intern <= 0 || team == 0 || female <= 0) {
					team = getTeam(female, male);
					break;
				}
				boolean isRemain = (team == getTeam(female, male)) ? true : false;
				if (isRemain) {
					if (intern <= 0)
						break;
					female--;
					intern--;
					if (team != getTeam(female, male)) {
						female++;
						male--;

						if (team != getTeam(female, male)) {
							team--;
						}
					} // }
				} else { // 팀이 깨졌다면, 남자를 뺀다
					team--;
					male--;
					intern--;
				}

			} else { // 팀을짜면 남자가 남는 경우로 남자를 뺀다
				team = female / 2;
				// if (intern > 0 && female > 0 && male > 0 && team > 0) {
				if (intern > 0) {
					male--;
					intern--;
				}
				boolean isRemain = (team == getTeam(female, male)) ? true : false;

				if (isRemain) {
					if (intern <= 0)
						break;
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

				// } // while
			} // else
		}
		System.out.print(team);
		System.out.println("ans :" + team);
		return team;
	}// main

	public static int getTeam(int female, int male) {
		if (female >= male * 2) {
			return male;
		} else {
			return female / 2;
		}
	}
}
