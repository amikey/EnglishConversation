package com.example.aswanabidin.englishconversation.Quiz;

/**
 * Created by aswanabidin on 11/6/17.
 */

public class QuestionData {

    // array untuk pertanyaan
    private String pertanyaan [] = {
            "1. Siapa nama anda?",
            "2. Dimana anda tinggal?",
            "3. Berapa umur nada sekarang?",
            "4. Dimana anda bersekolah?"
    };

    // array untuk pilihan jawaban dari setiap pertanyaan
    private String pilihanjawaban [][] = {
            {"Aswan","Aswin","Abidin","Asrul","Asdar"},
            {"asdas","makassar","Adsasda","asdsad","adsada"},
            {"asdada","21tahun","asasd","asda","asadsa"},
            {"Asdad","qfwefw","smaga","asfaftt","fdsfsd"}
    };

    // array untuk jawaban yang benar
    private String jawabanbenar[] = {"Aswan","makassar","21tahun","smaga"};


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



