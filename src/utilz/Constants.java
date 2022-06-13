package utilz;

public class Constants {

    public static class Directions{
        public static final int LEFT = 0;
        public static final int UP = 1;
        public static final int RIGHT = 2;
        public static final int DOWN = 3;
    }

    public static class PlayerConstants{

        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;



        public static int GetSpriteAmount(int player_action){

            switch (player_action) {

                case RUNNING:
                    return 3; //numero de imagenes que va a reproducir al correr
                case IDLE:
                    return 3; // numero de imagenes que va a reconocer al momento de estar quieta
                case FALLING:
                    return 3;
                case JUMP:
                    return 3;
                default:
                    return 0;

            }

        }

    }

}
