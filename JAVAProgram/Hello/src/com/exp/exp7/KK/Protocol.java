package com.exp.exp7.KK;

public class Protocol {
    private static final int WAITING = 0;
    private static final int SENTKNOCKKNOCK = 1;
    private static final int SENTCLUE = 2;
    private static final int SENTANSWER = 3;
    private static final int NUMJOKES = 8;
    private int state = WAITING;
    private int currentJoke = 0;

    private String[] clues = {"Buster", "Orange", "Ice cream", "Tunis",
    "Old lady", "Yah", "Dishes", "Amish"};
    private String[] answers = {"Buster...", "Orange...", "Ice cream...", "Tunis...",
            "Old lady...", "Yah...", "Dishes...", "Amish..."};

    public String protocolWorking(String question) {
        String answer = null;
        switch (state) {
            case WAITING:
                answer = "Knock! Knock!";
                state = SENTKNOCKKNOCK;
                break;
            case SENTKNOCKKNOCK:
                if (question.equalsIgnoreCase("who's there?")) {
                    answer = clues[currentJoke];
                    state = SENTCLUE;
                } else {
                    answer = "你应该问：\"Who's there?\"" + "重新开始：Knock! Knock!";
                }
                break;
            case SENTCLUE:
                if (question.equalsIgnoreCase(clues[currentJoke] + " Who?")) {
                    answer = answers[currentJoke] + " 是否继续？(y / n ?)";
                    state = SENTANSWER;
                } else {
                    answer = "你应该问：\"" + clues[currentJoke] + " Who?\"" + "重新开始：Knock! Knock!";
                    state = SENTKNOCKKNOCK;
                }
                break;
            case SENTANSWER:
                if (question.equalsIgnoreCase("y")) {
                    answer = "Knock! Knock!";
                    if (currentJoke == NUMJOKES - 1) {
                        currentJoke = 0;
                    } else {
                        currentJoke++;
                    }
                    state = SENTKNOCKKNOCK;
                } else {
                    answer = "Game Over! Goodbye!";
                    state = WAITING;
                }
                break;
        }
        return answer;
    }
}
