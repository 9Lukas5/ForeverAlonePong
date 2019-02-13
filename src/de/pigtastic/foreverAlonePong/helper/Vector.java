package de.pigtastic.foreverAlonePong.helper;

public class Vector {
	
	double x;
	double y;
	
	public Vector(){
	}
	public Vector(double x, double y){
		this.x=x;
		this.y=y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	/**
	 * Calculates the Vector of the Ball from last Postion and new Position
	 * 
	 * @param x1 startpunkt
	 * @param y1 startpunkt
	 * @param x2 endpunkt
	 * @param y2 endpunkt
	 */
	public void calcVector(double x1, double y1, double x2, double y2) {
		this.x=x2-x1;
		this.y=y2-y1;
	}
	@Override
	public String toString() {
		return "Vector [x=" + x + ", y=" + y + "]";
	}
	
	
	/**
	 * Calculate the scalar of two Vectors
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double scalarProdukt(Vector v1, Vector v2) {
		double scalar =(v1.getX()*v2.getX())+(v1.getY()*v2.getY());
		return scalar;
		
	}
	/**
	 * Calculates the length of a Vector
	 * @return the vector length
	 */
	public double length() {
		double length=Math.sqrt(Math.pow(this.x, 2)+Math.pow(this.y, 2));
		return length;
	}
	
	/**
	 * Angle between two Vectors
	 */
	public static double angle(Vector v1, Vector v2) {
		double angle= Math.acos(scalarProdukt(v1, v2)/(v1.length()*v2.length()));
		return angle;
		
	}
	
	/**
	 * change the length of the vector to One.
	 */
	public void toUnitVector() {
		double scalar = Math.sqrt(Math.pow(x, 2)+Math.pow(y, 2));
		this.x=x/scalar;
		this.y=y/scalar;
	}
	
	

	

}