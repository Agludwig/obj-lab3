    public enum Direction {
        NORTH("North"), EAST("East"), SOUTH("South"), WEST("West");
    
        private final String name;
        Direction(String name) {this.name = name;}
    
        @Override
        public String toString() {return name;}

        public double[] toPosition() {
            return switch (this) {
                case NORTH -> new double[]{0, 1};
                case EAST -> new double[]{1, 0};
                case SOUTH -> new double[]{0, -1};
                case WEST -> new double[]{-1, 0};
            };
        }

        public Direction turnLeft() {
            return switch (this) {
                case NORTH -> WEST;
                case WEST -> SOUTH;
                case SOUTH -> EAST;
                case EAST -> NORTH;
            };
        }

        public Direction turnRight() {
            return switch (this) {
                case NORTH -> EAST;
                case EAST -> SOUTH;
                case SOUTH -> WEST;
                case WEST -> NORTH;
            };
        }
    } 