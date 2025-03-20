import math
def polysum(n, s):
    '''
    area of a regular polygon is (0.25*n*s^2)/tan(pi/n)
    perimeter of regular polygon is (n*s)

    Args: 
        n(int/float): Number of sides
        s(int/float): Lenght of sides

    Returns:
        (int/float) rounded to 4th place of area + perimeter squared
    '''
    areaOfPolygon = ((0.25 * n * (s**2))/(math.tan(math.pi/n)))
    perimeterOfPolygonSquared = (n*s)**2
    areaPlusPerimeter = areaOfPolygon + perimeterOfPolygonSquared
    return round(areaPlusPerimeter, 4)