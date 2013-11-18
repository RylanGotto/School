package comparisonObjects;
/**
 * This interface is used to allow for comparison of objects in the Link List Class
 * @author JKidney
 */
public interface CompareObjects<type> 
{
	/**
	 * This method will return a comparison of the two objects. It should return
	 * the following values based upon comparing obj1 and obj2
	 *       (1) if obj1 < obj2   return -1
	 *       (2) if obj1 == obj2  return  0
	 *       (2) if obj1 > obj2   return  1
	 * @param obj1 the first object in the compare
	 * @param obj2 the second object in the compare
	 * @return -1 for less then, 0 for equals and 1 for greater than
	 */
    public int compare(type obj1, type obj2);
}
