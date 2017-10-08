/**
 * Question 2 queries for the top ten pairs of individuals and their mutual friend lists ranked by count
 * of mutual friends.  It is assumed that if (A <--> B) and (B <--> C) but (A <-/-> C), then (A,C) do
 * indeed have the mutual friend (B).  Note that this is a different definition of "mutual friend" than is
 * used in Question 1.
 * <p>
 * This query is split into three sequential jobs.  The first job transforms the friendship graph into a
 * set of <code><(user1,user2), mutualFriend></code> atoms.   The second job groups the set of mutual
 * friend atoms into a set comprising one mutual friend list for each pair of users, where user pairs
 * without any mutual friends are omitted.  The third job selects and orders the top ten user pairs and
 * their mutual friend lists ranked by count of mutual friends.  The intermediate output of the first and
 * second jobs is written to a temporary file that is removed following the third job.
 * <p>
 * {@link question2.Driver} contains the <code>main</code> method for this query.
 */
package question2;