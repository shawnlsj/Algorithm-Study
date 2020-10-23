package etc.simulation;

import binary_serach.Programmers_43238;

import java.util.*;

public class Programmers_42893 {
    public static void main(String[] args) {
        Programmers_42893 p = new Programmers_42893();
        String word = "Muzi";
        String[] pages = new String[]
                {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"}
                ;
        System.out.println(p.solution(word, pages));
    }
    public int solution(String word, String[] pages) {
        HashMap<String, Page> map = new HashMap<>();

        for (int i = 0; i < pages.length; i++) {
            StringTokenizer stk = new StringTokenizer(pages[i]);
            boolean isHeadTag = false;
            boolean isMetaTag = false;
            boolean isBodyTag = false;

            String siteName = "";
            String linkToken = null;
            while (stk.hasMoreTokens()) {
                String token = stk.nextToken();
                String lowerWord = word.toLowerCase();
                String lowerToken = token.toLowerCase();
                if (token.equals("<head>")) {
                    isHeadTag = true;
                }
                else if (isHeadTag && token.equals("<meta")) isMetaTag = true;
                else if (isMetaTag && token.contains("content")) {
                    siteName = token.substring(token.indexOf("\"") + 1, token.lastIndexOf("\""));
                    if (map.get(siteName) == null) map.put(siteName, new Page(i));
                    isMetaTag = false;
                } else if (token.equals("<body>")) {
                    isBodyTag = true;
                } else if (isBodyTag && lowerToken.contains(lowerWord)) {
                    lowerToken = lowerToken.replaceFirst(lowerWord, "");
                    if (!lowerToken.contains(lowerWord)) {
                        map.get(siteName).defaultScore++;
                    }
                } else if (linkToken != null) {
                    if (token.charAt(token.length() - 1) == '>') {
                        String linkSiteName = token.substring(token.indexOf("\"") + 1, token.lastIndexOf("\""));
                        map.get(siteName).linkPages.add(linkSiteName);
                    }
                    linkToken = null;
                } else if (token.equals("<a")) {
                    linkToken = token;
                }
            }
        }

        // 링크 점수 구하기
        Iterator<Map.Entry<String, Page>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Page page = it.next().getValue();
            for (int i = 0; i < page.linkPages.size(); i++) {
                String linkSiteName = page.linkPages.get(i);
                map.get(linkSiteName).linkScore += (double) page.defaultScore / page.linkPages.size();
            }
        }

        // 매칭 점수 구하기
        it = map.entrySet().iterator();
        while (it.hasNext()) {
            Page page = it.next().getValue();
            page.matchingScore = page.defaultScore + page.linkScore;
        }

        // 매칭 점수 별로 정렬하기
        List<String> pageNameList = new ArrayList<>(map.keySet());
        pageNameList.sort((o1, o2) -> {
            if (map.get(o1).matchingScore != map.get(o2).matchingScore) {
                double result = map.get(o2).matchingScore - map.get(o1).matchingScore;
                if (result > 0) {
                    return 1;
                } else if (result == 0) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                return map.get(o1).pageIndex - map.get(o2).pageIndex;
            }
        });

        return map.get(pageNameList.get(0)).pageIndex;
    }
    class Page{
        int pageIndex;

        int defaultScore;
        double linkScore;
        ArrayList<String> linkPages = new ArrayList<>();
        double matchingScore;

        Page(int pageIndex) {
            this.pageIndex = pageIndex;
        }
    }
}
