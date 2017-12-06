# CS 141 Robocode Report
Team AboveAverageBot
Michael Hernandez and Sebastian Hernanddez

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

The result of last minute changes unintentially resulted in our random movement becoming circular movement. Also unintentially it became
