#include <iostream>
#include <cmath>
#include <vector>

struct Vector2 {
public:
	Vector2() {}
	Vector2(double x, double y) {
		this->x = x;
		this->y = y;
	}

	Vector2& operator +=(const Vector2& rhs) { this->x += rhs.x; this->y += rhs.y; return *this; }
	Vector2& operator -=(const Vector2& rhs) { this->x -= rhs.x; this->y -= rhs.y; return *this; }
	Vector2& operator *=(double rhs) { this->x *= rhs; this->y *= rhs; return *this; }
	Vector2& operator /=(double rhs) { this->x /= rhs; this->y /= rhs; return *this; }

	double x = 0;
	double y = 0;
};

Vector2 operator+(const Vector2& value) { return value; }
Vector2 operator-(const Vector2& value) { return Vector2(-value.x, -value.y); }
Vector2 operator+(const Vector2& lhs, const Vector2& rhs) { return Vector2(lhs.x + rhs.x, lhs.y + rhs.y); }
Vector2 operator-(const Vector2& lhs, const Vector2& rhs) { return Vector2(lhs.x - rhs.x, lhs.y - rhs.y); }
Vector2 operator*(const Vector2& lhs, double rhs) { return Vector2(lhs.x * rhs, lhs.y * rhs); }
Vector2 operator*(double lhs, const Vector2& rhs) { return rhs * lhs; }
Vector2 operator/(const Vector2& lhs, double rhs) { return Vector2(lhs.x / rhs, lhs.y / rhs); }
double Dot(const Vector2& lhs, const Vector2& rhs) { return lhs.x * rhs.x + lhs.y * rhs.y; }
double Magnitude(const Vector2& value) { return std::sqrt((value.x * value.x) + (value.y * value.y)); }
bool operator==(const Vector2& lhs, const Vector2& rhs) { return lhs.x == rhs.x && lhs.y == rhs.y; }
bool operator!=(const Vector2& lhs, const Vector2& rhs) { return !(lhs == rhs); }

struct Circle {
public:
	Circle() { }
	Circle(Vector2 center, double radius) {
		this->center = center;
		this->radius = radius;
	}
	Vector2 center;
	double radius = 0;
};

int main(void) {
	int simNum;
	std::vector<int> results;
	
	Vector2 center;
	double radius;

	std::cin >> simNum;
	for (int n = 0; n != simNum; ++n) {
		Circle largeCircle, smallCircle;
		std::cin >> center.x >> center.y >> radius;
		largeCircle = Circle(center, radius);
		std::cin >> center.x >> center.y >> radius;
		smallCircle = Circle(center, radius);

		if (largeCircle.center == smallCircle.center && largeCircle.radius == smallCircle.radius) {
			results.push_back(-1);
			continue;
		}

		if (smallCircle.radius > largeCircle.radius) {
			std::swap(smallCircle, largeCircle);
		}

		double distance = Magnitude(largeCircle.center - smallCircle.center);

		if (distance == largeCircle.radius + smallCircle.radius) {
			results.push_back(1);
		} else if (distance > largeCircle.radius + smallCircle.radius) {
			results.push_back(0);
		} else {
			if (distance + smallCircle.radius > largeCircle.radius) {
				results.push_back(2);
			} else if (distance + smallCircle.radius < largeCircle.radius) {
				results.push_back(0);
			} else {
				results.push_back(1);
			}
		}
	}
	for (int result : results) {
		std::cout << result << "\n";
	}
}