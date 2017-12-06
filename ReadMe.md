# CS 141 Robocode Report
Team AboveAverageBot

Michael Hernandez and Sebastian Hernandez

For our robot, we intially had wanted to create a robot would:

- Move randomly
- Avoid walls
- Bullet power dependent on distance
- Have the gun/radar constantly rotated and fire at any detected enemy robot
- Dodge bullets

However, we decided not to go with the constantly rotating gun/radar and went with a tracking-like approach. Neither did we end up
implementing the ability to dodge bullets. The ability to track enemies, move randomly, and avoid walls initially worked well when
simulated against sample robots. Unfornately, it wasn't until it was too late that we noticed it began behaving very strangely and
buggy in simulations. Perhaps we made accidentally changed something in our final revisions.

The result of last minute changes unintentially resulted in our random movement becoming circular movement. As another unintentional result it became fairly effective at dodging bullets because of this movement, but was still weak against robots with predictive shooting. A positive though was that it was avoiding walls significantly better and was no longer twitching if it did collide.

Our shooting had its ups and downs. We liked that we got it to shoot only if a detected enemy was within a certain range. This preserved energy by not firing from distances where the enemy robot would have been out of the line of fire by the time the bullet got to its detected spot. The decision to have the bullet damage was another positive since it was dependent on the distance from the enemy. Despite the bullet damage not varying much due to our restrictions on when to fire, the minor points it saved do add up. Once again, and too late, we noticed our aiming was off due to a small miscalculation as well as not accounting for the unintentional change to circular movement.

Overall, our robot was pretty decent in 1v1 battles but it wasn't really prepared well for group battles. It essentially relied on a "dodge and weave" method where it would avoid bullets and allow the enemy to basically destroy themselves with the assistance of our bullets if they were in range.

One thing we would definitely have done differently was to use Netbeans (or another IDE) from the beginning. We ran into quite a lot of bugs with Robocode's built-int IDE and compilation system that cost us a lot of time and frustration. Regardless, this project was definitely a great learning experience in using and understanding custom APIs as well as learning to extensively test for all sorts of scenarios to avoid bugs. 
