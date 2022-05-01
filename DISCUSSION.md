
Already fresh out of gate, the first decision is how to handle a game  title.  
Should it just be a property on the gamer, or should it be a separate table?  

If it's in a separate table, it requires the game to exist prior the gamer enrollment.   

On the other hand, if we allow gamers to specify games themselves, it will take exactly 0,1 seconds before the database is filled with creatively named games like 'A M O G U S' or 'Crab Nicholson Extreme Sleepover Text Adventure'


Deciding on skill level.  
Once again, we encounter the 'user choice vs validation', since the levels are more or less s tatical, an enum is the easiest to handle.

Deciding on Gender.  
We'd better have a good justification to ensure we're not hit by GDPR regulations.  
  
Deciding on ID.  
In a real world scenario, we would use UUIDs, as people will probably create joke accounts, spam accounts etc.  
Steam has amassed ~1 billion users throughout it's existence, so it's a little under halfway there to a max int value of 2.147.483.647  

I chose integer for IDs, because it's a tinkering project.  
  
# Task 1 - Search API based on gamers levels, games and geography for auto-matching.  
Should be simple, let the user supply above mentioned properties, search all enrollments based on those parameters.  
This does pose a problem, as the skill level resides on Gamer level, and we look for enrollments, forcing us to do joins.  
  
- We'd have to do that anyway, as we are looking for users. 😅

- I'm starting to believe that hte data-modeling is better suited for a document database

At this point, I've coded myself into a corner with:  

```java
List<User> findAllBySkillAndRegionAndGamesIsIn(Skillz skillLevel, Region region, List<Game> games);
```

It is a crude implementation, alternatively it could be solved by using query examples.  
  
# Task 2 - Credits (I am credit to team!)

Once the initial game, user and association table(s) were done, adding credit was fairly straightforward.  
  
Since a user can have a credit in every team, a uni-literal OneToMany relationship made sense.  
Same goes for Credits-Game, as a credit can only be tied to one game. 


## Doubts about interest and games  
in the task it specifies >Gamers individual interests with their levels (noob, pro, invincible)  
On second glance, does this imply that the gamers s hould have the skill level on a per-game basis?  


# Validation handling  
  
I must be missing something, as the ControllerAdvice-based solution for presenting errors in a 'pretty' format - seems very cumbersome
  

# To DTO or not to DTO
At some point, you want to not expose your entities to the outside world, but instead do projections/mappings of them  
But for the sake of simplicity, I chose not to.

# Api documentation
I chose springdoc, as far as I know, springfox isn't yet ready for spring boot 2.6

# Testing

Given how little logic there was, I don't agree with the ask to build unit tests, as most is either validation or database.  
Given time constriants, I've only built controller test.

## Test data
Initially it was easier to create entries into database programatically, buth when I reached 10+ entites, it was too late. LOL.


# What I didn't manage to do
My HQL is a tad rusty, so I had trouble getting the high-scorer by game and skill level.  
It works via SQL, but HQL wouldn't have any of it.  
```sql
select c.user_id, max(c.credit), c.game_name, u.skill, u.name
from credit as c
LEFT JOIN users u on c.user_id = u.id
group by c.game_name, u.skill
```

