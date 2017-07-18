import java.util.ArrayList;

public class TestCollisions {

    float T = 0.01f;

    //
    boolean checkCollision(Vector rectP, Vector rectSz, float rectD,  Vector ballP, Vector ballV){

        //top left is P, bottom right is p + rectSz
        Vector bottomright = Vector.add(rectP, rectSz);
        Vector topright = new Vector(rectP.x + rectSz.x, rectP.y);
        Vector bottomleft = new Vector(rectP.x, rectP.x + rectSz.y);

        Vector ballCheck = Vector.add(ballP, Vector.mult(ballV, T));

        ArrayList<Vector> corners = new ArrayList<Vector>(){{
            add(rectP);
            add(topright);
            add(bottomright);
            add(bottomleft);
        }};

        for(int i = 1; i < corners.size(); i++){
            if(intersecting(corners.get(i-1), corners.get(i), ballP, ballCheck)) {
                modifyVelocity(Vector.sub(corners.get(i - 1), corners.get(i)), ballV);
                return true;
            }
        }

        return false;
    }


    boolean intersecting(Vector A, Vector B, Vector P, Vector Q){
        int case1 = (int)(((B.x - A.x) * (P.y - B.y)) - ((B.y - A.y) * (P.x - B.x)));
        int case2 = (int)(((B.x - A.x) * (Q.y - B.y)) - ((B.y - A.y) * (Q.x - B.x)));
        return !((case1 > 0 && case2 > 0) || (case1 < 0 && case2 < 0));
    }


    void modifyVelocity(Vector wall, Vector ballV){
        ballV.rotate(Vector.getAngle(ballV, wall) * -2.0f);
    }

}
