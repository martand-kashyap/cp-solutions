package heap;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
class Point {
    int x;
    int y;

    int distanceFromPoint(int x, int y) {
        return (int) (Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
    }

    int distanceFromOrigin() {
        return (int) (Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }
}
