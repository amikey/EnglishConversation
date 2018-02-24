package com.mumtaazstudio.aswanabidin.englishconversation.Quiz;

/**
 * Created by aswanabidin on 11/7/17.
 */

public class MediumQuestionData {

    // array untuk pertanyaan
    private String pertanyaan [] = {
            "Man : Did chuck call before or after the class? \n" +
                    "Woman : He called during the class \n\n " +
                    "What did the woman say?\n",
            "Man : I can’t figure out how Brende knew how to fix the car\n" +
                    "Woman : She used to work in a repair shop.\n\n" +
                    "What does the woman say about Brende?\n",
            "Woman : I am not sure how to deal with this. Should I tell the authorities or forget about it?\n" +
                    "Man : How could you question it?\n" +
                    "Woman : You’re right. I’ll make the call in the morning.\n\n" +
                    "What’s the woman problem?\n",
            "Man : wouldn’t you rather take a break now? we’ve been at it for hours.\n" +
                    "Woman : I’m beat, too. let’s get something to eat.\n" +
                    "Man : We’ll be able to concentrate with a little nutrition.\n\n" +
                    "What do the speakers mean?\n",
            "Woman : Why won’t you let heather find herself? you can’t make her decisions forever.\n" +
                    "Man : I’m just not ready to let go, to admit that she’s an adult now\n" +
                    "Woman : Well she is and I think she is ready to show it.\n\n" +
                    "What does the woman suggest that the man do?\n",
            "Man : I need to talk to you. I am going into the hospital for a few days. You’ll need to take over management.\n" +
                    "Woman : oh, I am sorry. Sure, I’ll be happy to help. But, I hope you will return as good as ever very soon.\n" +
                    "Man : I’m optimistic. But, I know you’ll take care of things one way or the other.\n\n" +
                    "What do the speakers mean?\n",
            "Man : Don’t bring up the topic we discussed last night while jeff is here. He doesn’t agree with the decision at all.\n" +
                    "Woman : I’d like hear his point of view. The subject isn’t closed yet.\n" +
                    "Man : You’ll be sorry.\n\n" +
                    "What does the man mean about jeff?\n",
            "Man : So, Did the diet help?\n" +
                    "Woman : It was the exercise that did it. The diet wasn’t much of use.\n\n" +
                    "How did the woman lose weight?\n",
            "Woman : We’ll never make the fligth in time\n" +
                    "Man : Not at the speed you’re driving\n\n" +
                    "What would the man advise the woman to do?\n",
            "Man : The best wine does not only depend on the amount of sunshine\n" +
                    "Woman : No, it also depends on whether there’s frost in the winter\n\n" +
                    "What could be a problem for grape\n"

    };

    // array untuk pilihan jawaban dari setiap pertanyaan
    private String pilihanjawaban [][] = {
            {"He did not call","He called while the class was going on","He called again","He called before the class",
            "He called after the class"},
            {"She didn’t know what to do","She know what to do","She used an auto repair manual",
                    "She wishes she still had her old job","She has experience as a mechanic"},
            {"She isn’t sure whether to report something","She is distressed that somebody knows what she did",
                    "She is angry about the contract","She is angry about the problem","She wanted to ask a question"},
            {"They’re tired","The woman is worried about the man’s anger","They eating nutrition","They feel refreshed",
                    "They broke their table"},
            {"Give heather more freedom","Her decisions forever","Give up on heather","Discipline Heather",
                    "Be more involved in heather’s life"},
            {"The man is retiring for good","The woman is pleased that the man is leaving","The man is care of things",
                    "The woman doesn’t care for the current management","The man is temporarily turning things over the woman"},
            {"They discussed last night","He is studying","He hasn’t made up his mind","He is very much against the issue that the man promotes",
                    "He isn’t aware of what the man and woman are discussing"},
            {"She went on a diet","She read some exercises on dieting","He didn’t use the diet",
                    "She didn’t use the diet","She took exercise"},
            {"Drive faster","Drive slow","Drive, not go by plane","Slow down","Not to worry"},
            {"If there’s too much sunshine","If the sunshine very hot","If there’s too little sunshine","If the winter is very cold",
                    "If they are picked in the winter"}

    };

    // array untuk jawaban yang benar
    private String jawabanbenar[] = {"He called while the class was going on","She has experience as a mechanic",
            "She isn’t sure whether to report something","They’re tired","Give heather more freedom",
            "The man is temporarily turning things over the woman","He is very much against the issue that the man promotes",
            "He is very much against the issue that the man promotes","She took exercise","Drive faster","If the winter is very cold"};



    // method untuk mengembalikan nomor pertanyaan
    public int getLength() {
        return pertanyaan.length;
    }

    // method untuk mengembalikan pertanyaan dari array pertanyaan[] dalam array index
    public String getPertanyaan(int a) {
        String question = pertanyaan[a];
        return  question;
    }

    // method return a single multiple single chocie item for question based on array index,
    // based on number of multiple choice item in the list - 1, 2, 3, or 4 as an argument
    public String getPilihanJawaban(int index, int num) {
        String pilJawaban = pilihanjawaban[index][num-1];
        return pilJawaban;
    }

    // method untuk mengembalikan jawaban benar dari pertanyaan dalam array index
    public String getJawabanBenar(int a){
        String answer = jawabanbenar[a];
        return answer;
    }

}
