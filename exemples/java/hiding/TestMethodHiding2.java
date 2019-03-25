
public class TestMethodHiding2{
    
    public static void main(String[] args){
        Animal[] animaux = {
            new Chien(),
            new Chat()
        };
        for(Animal a : animaux){
            System.out.println(a.grogner());
        }
    }
}

class Animal{
    public static String grogner(){
        return "grogner() dans Animal";
    }
}
class Chat extends Animal {
    // @Override
    public static String grogner(){
        return "Miaou";
    }
}
class Chien extends Animal {
    // @Override
    public static String grogner(){
        return "Ouaf";
    }
}

