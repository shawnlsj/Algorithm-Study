package etc.simulation;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//매칭 점수
public class Programmers_42893 {
<<<<<<< HEAD
  
=======
    public static void main(String[] args) {
        Programmers_42893 p = new Programmers_42893();
        String word = "blind";
        String[] pages = new String[]
                {"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"};
        System.out.println(p.solution(word, pages));
    }

    public int solution(String word, String[] pages) {
        HashMap<String, Page> map = new HashMap<>();

        for (int i = 0; i < pages.length; i++) {
            String page = pages[i];
            String url = "";
            Pattern urlPattern = Pattern.compile("<meta property=\"og:url\" content=\"\\S+\"");
            Matcher urlMatcher = urlPattern.matcher(page);

            if (urlMatcher.find()) {
                url = urlMatcher.group();
                url = url.replaceAll("(\\s|\\S)*content=\"", "");
                url = url.replaceAll("\"", "");
                if (map.get(url) == null) {
                    map.put(url, new Page(i));
                }
            }

            Pattern linkPattern = Pattern.compile("<a href=\"\\S*\">");
            Matcher linkMatcher = linkPattern.matcher(page);
            while (linkMatcher.find()) {
                String link = linkMatcher.group();
                link = link.replaceAll("<a href=\"", "");
                link = link.replaceAll("\">", "");
                map.get(url).linkPages.add(link);
            }

            Pattern wordPattern = Pattern.compile("[a-zA-Z]*(?i)"+word+"[a-zA-Z]*");
            Matcher wordMatcher = wordPattern.matcher(page);
            while (wordMatcher.find()) {
                if (wordMatcher.group().length() == word.length()) {
                    map.get(url).defaultScore++;
                }
            }
        }
        // 링크 점수 구하기
        Iterator<Map.Entry<String, Page>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Page page = it.next().getValue();
            for (int i = 0; i < page.linkPages.size(); i++) {
                String linkSiteName = page.linkPages.get(i);
                if (map.get(linkSiteName) == null) {
                    continue;
                }
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

    class Page {
        int pageIndex;

        long defaultScore;
        double linkScore;
        ArrayList<String> linkPages = new ArrayList<>();
        double matchingScore;

        Page(int pageIndex) {
            this.pageIndex = pageIndex;
        }
    }
>>>>>>> origin/master
}
