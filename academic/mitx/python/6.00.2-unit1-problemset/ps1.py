###########################
# 6.00.2x Problem Set 1: Space Cows 

from ps1_partition import get_partitions
import time

#================================
# Part A: Transporting Space Cows
#================================

def load_cows(filename):
    """
    Read the contents of the given file.  Assumes the file contents contain
    data in the form of comma-separated cow name, weight pairs, and return a
    dictionary containing cow names as keys and corresponding weights as values.

    Parameters:
    filename - the name of the data file as a string

    Returns:
    a dictionary of cow name (string), weight (int) pairs
    """

    cow_dict = dict()

    f = open(filename, 'r')
    
    for line in f:
        line_data = line.split(',')
        cow_dict[line_data[0]] = int(line_data[1])
    return cow_dict


# Problem 1
def greedy_cow_transport(cows, limit=10):
    """
    Uses a greedy heuristic to determine an allocation of cows that attempts to
    minimize the number of spaceship trips needed to transport all the cows. The
    returned allocation of cows may or may not be optimal.
    The greedy heuristic should follow the following method:

    1. As long as the current trip can fit another cow, add the largest cow that will fit
        to the trip
    2. Once the trip is full, begin a new trip to transport the remaining cows

    Does not mutate the given dictionary of cows.

    Parameters:
    cows - a dictionary of name (string), weight (int) pairs
    limit - weight limit of the spaceship (an int)
    
    Returns:
    A list of lists, with each inner list containing the names of cows
    transported on a particular trip and the overall list containing all the
    trips
    """
    # TODO: Your code here
    """Assumes item a list, maxCost >= 0,
    keyFunction maps elements of items to numbers
    """

    # keyFunction allows us to define the 'BEST' order
    sortedCows = sorted(cows.items(), key=lambda item: item[1], reverse=True)
    # sorting can be done in n log n time 
    # where n = len(items)
    result = []

    #print(sortedCows)
    #print(sortedCows[0])
    #print(sortedCows[0][1])

    while sortedCows:
        #print('######################################################')
        ship = []
        totalCost = 0
        #print(sortedCows)
        copySortedCows = sortedCows.copy()
        for cow in copySortedCows:
            #print('-------------------------------------------------')
            #print(f'Name: {cow[0]}, Weight: {cow[1]}')
            #print(f'Is totalCost plus cow ({totalCost+int(cow[1])}) over the limit ({limit}))')
            if(totalCost+int(cow[1])) <= limit:
                #print("Added to ship")
                ship.append(cow[0])
                totalCost += cow[1]
                sortedCows.remove(cow)
            #else:
                #print("Skipped")
            
            #print(f'Total weight is at {totalCost}, and limit is {limit}')
        result.append(ship)
        print(f'Current ship -- {ship} and weight {totalCost}')

    return (result)


# Problem 2
def brute_force_cow_transport(cows,limit=10):
    """
    Finds the allocation of cows that minimizes the number of spaceship trips
    via brute force.  The brute force algorithm should follow the following method:

    1. Enumerate all possible ways that the cows can be divided into separate trips
    2. Select the allocation that minimizes the number of trips without making any trip
        that does not obey the weight limitation
            
    Does not mutate the given dictionary of cows.

    Parameters:
    cows - a dictionary of name (string), weight (int) pairs
    limit - weight limit of the spaceship (an int)
    
    Returns:
    A list of lists, with each inner list containing the names of cows
    transported on a particular trip and the overall list containing all the
    trips
    """
    # TODO: Your code here
    result = []
    sortedCows = sorted(cows.items(), key=lambda item: item[1], reverse=True)
    while sortedCows:
        ship = []
        #print('##############################')
        #print(f'Current cows - {sortedCows}')     
        copySortedCows = sortedCows.copy()   
        ship = max_cow_per_ship(copySortedCows, limit)
        #print(f'Current ship - {ship}')

        #print(f'Just the cows - {ship[1]}')
        shipJustNames = []
        for cow in ship[1]:
            sortedCows.remove(cow)
            shipJustNames.append(cow[0])
            #print(f'Loop {cow}')

        print(f'Current ship - {shipJustNames} and weight {ship[0]}')
        result.append(shipJustNames)

    return result

def max_cow_per_ship(cows, limit):
    #print('-------------------------------------------')
    #print(f'Cows -- {cows}')
    #print('Weight: ' + str(cows[0][1]))
    #print(f'Cows reduced -- {cows[1:]}')
    if cows == [] or limit == 0:
            result = (0, ())
    elif cows[0][1] > limit:
        #Explore right branch only
        result = max_cow_per_ship(cows[1:], limit)
    else:
        nextCow = cows[0]
        nextCowWeight = cows[0][1]
        #Explore left branch
        withVal, withToTake = max_cow_per_ship(cows[1:], limit - nextCowWeight)
        withVal += nextCowWeight
        #Explore right branch
        withoutVal, withoutToTake = max_cow_per_ship(cows[1:], limit)
        #Choose better branch
        if withVal > withoutVal:
            result = (withVal, withToTake + (nextCow,))
        else:
            result = (withoutVal, withoutToTake)

    #print(f'Result -- {result}')
    return result

        
# Problem 3
def compare_cow_transport_algorithms():
    """
    Using the data from ps1_cow_data.txt and the specified weight limit, run your
    greedy_cow_transport and brute_force_cow_transport functions here. Use the
    default weight limits of 10 for both greedy_cow_transport and
    brute_force_cow_transport.
    
    Print out the number of trips returned by each method, and how long each
    method takes to run in seconds.

    Returns:
    Does not return anything.
    """
    # TODO: Your code here
    cows = load_cows("ps1_cow_data.txt")
    limit=25
    greedy_start_time = time.time()
    greedy = greedy_cow_transport(cows, limit)
    greedy_end_time = time.time()
    greedy_elapsed = greedy_end_time - greedy_start_time

    brute_start_time = time.time()
    brute = brute_force_cow_transport(cows, limit)
    brute_end_time = time.time()
    brute_elapsed = brute_end_time - brute_start_time

    print(f'Greedy has {len(greedy)} ships - took {greedy_elapsed} seconds')
    print(f'Brute has {len(brute)} ships - took {brute_elapsed} seconds')


"""
Here is some test data for you to see the results of your algorithms with. 
Do not submit this along with any of your answers. Uncomment the last two
lines to print the result of your problem.
"""

cows = load_cows("ps1_cow_data.txt")
limit=10
print(cows)

print(greedy_cow_transport(cows, limit))
print(brute_force_cow_transport(cows, limit))

compare_cow_transport_algorithms()

