class Expressions{
    
    public static void main(String[] args) {
        int a;
        int b = (a=3);
        System.out.println("a=3 \t: " + b);
        
        System.out.println("------------------");
        if((a = 2) == 3){
            System.out.println("a = 3");
        } else {
            System.out.println("a != 3");
        }
        System.out.println("a = " + a);
        
        System.out.println("------------------");
        int x = 4;
        int y = (x *= 2);
        System.out.println("x *= 2 \t: " + y);
        
        System.out.println("------------------");
        int i = 10;
        int j;
        j = (i++);
        System.out.println("i++ \t: " + j);
        j = (++i);
        System.out.println("++i \t: " + j);
    }
}
