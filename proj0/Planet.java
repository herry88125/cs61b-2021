public class Planet {
	
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double G = 6.67e-11;

	public Planet(double xP, double yP, double xV,
              double yV, double m, String img) 
    {
    	xxPos = xP; yyPos = yP;
    	xxVel = xV; yyVel = yV;
    	mass = m; 
    	imgFileName = img;
    }

    public Planet(Planet p) {
    	xxPos = p.xxPos; yyPos = p.yyPos;
    	xxVel = p.xxVel; yyVel = p.yyVel;
    	mass = p.mass;
    	imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
    	double dx = this.xxPos - p.xxPos;
    	double dy = this.yyPos - p.yyPos;
    	return Math.sqrt(dx * dx + dy * dy);
    }   

    public double calcForceExertedBy(Planet p) {
    	double m1 = this.mass;
    	double m2 = p.mass;
    	double r = this.calcDistance(p);
    	return G * m1 * m2 / (r * r);
    } 

    public double calcForceExertedByX(Planet p) {
    	double dx = p.xxPos - this.xxPos;
    	double r = this.calcDistance(p);
    	double f = this.calcForceExertedBy(p);
    	return f * dx / r;
    }  

    public double calcForceExertedByY(Planet p) {
    	double dy = p.yyPos - this.yyPos;
    	double r = this.calcDistance(p);
    	double f = this.calcForceExertedBy(p);
    	return f * dy / r;
    }  

    public double calcNetForceExertedByX(Planet[] planets) {
    	// F(net, x) = F(1, x) + F(2, x) + ...
    	double sum = 0;
    	for(Planet p : planets) {
    		if(p == this) {
    			continue;
    		}
    		sum = sum + this.calcForceExertedByX(p);
    	}
    	return sum;
    }

     public double calcNetForceExertedByY(Planet[] planets) {
    	// F(net, y) = F(1, y) + F(2, y) + ...
    	double sum = 0;
    	for(Planet p : planets) {
    		if(p == this) {
    			continue;
    		}
    		sum = sum + this.calcForceExertedByY(p);
    	}
    	return sum;
    }

    public void update(double dt, double fX, double fY) {
    	double aX = fX / mass;
    	double aY = fY / mass;
    	xxVel = xxVel + aX * dt;
    	yyVel = yyVel + aY * dt;
    	xxPos = xxPos + xxVel * dt;
    	yyPos = yyPos + yyVel * dt;
    }

    public void draw() {
    	StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}