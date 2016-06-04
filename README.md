# LazyStorage
A simple way to persist objects in Android without knowing anything about databases.

## Why?
There are a lot of ORM and database libraries to help you add data persistence into your app, but sometimes you don't need a lot of tables and relationships. In some cases you just need to save a few objects to let your users use your app offline.

I tried Realm, ORLite and SugarORM. They are cool libraries, but there are some things I dislike:

* Modify my data model classes to extend a **DB record superclass**.
* Add unnecessary annotations like **@Table**, **@PrimaryKey**, **@Unique**, etc.
* Some complex solutions to persist **1-1** and **1-N** relationships.

## What?
I coded a simple library to help you store objects modifying your project as less as possible. LazyStorage obtains an id by reflection:

* Invoking a **getId()** method.
* Looking for an **id** or **mId** field.
* Looking for a field annotated with **@Id** (Have I said I don't like to add extra annotations to my data model?). Maybe you don't have ids, perhaps you have *username*, *passport*, or other unique field.

It saves that id and the value of the object (serialized with **Gson**).


##TL;DR;

First of all, create a LazyStorage object:

~~~java
LazyStorage lazy = new LazyStorage(context);
~~~

Then, suppose we have this class:

~~~java
public class User {
    private long id;
    private String name;
    private String surname;

    // Getters and Setters and other stuff
}    
~~~

To save one object just call:

~~~java
lazy.save(user);
~~~

To load it again:

~~~java
User user = lazy.load(id, User.class);
~~~

It's possible to do the same with collections:

~~~java
List<Comment> comments = api.getComments();
lazy.saveAll(comments);
~~~

...and recover them...

~~~java
List<Comment> comments = lazy.loadAll(Comment.class);
~~~

If you need to delete something:

~~~java
// One specific instance
lazy.delete(id, User.class);

// All objects of one class
lazy.deleteAll(Comment.class);
~~~

And if you want to delete everything:

~~~java
lazy.invalidate();
~~~

## Maven

The library is in *JCenter*. You just need to add this dependency:
~~~
compile 'com.martinchamarro.lazystorage:lazystorage:1.0'
~~~

####Please, let me know if you use LazyStorage!!! ;D
