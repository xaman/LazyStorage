# LazyStorage

[![jcenter](https://img.shields.io/badge/jcenter-1.1.0-green.svg)](https://bintray.com/martin-chamarro/maven/lazystorage/)

A simple way to persist objects in Android without knowing anything about databases.

## Why?
There are a lot of ORM and database libraries to help you add data persistence into your app, but sometimes you don't need a lot of tables and relationships. In some cases you just need to save a few objects to let your users use your app offline.

I tried Realm, ORLite and SugarORM. They are cool libraries, but there are some things I dislike:

* Modify my data model classes to extend a **DB record superclass**.
* Add unnecessary annotations like **@Table**, **@PrimaryKey**, **@Unique**, etc.
* Some complex solutions to persist **1-1** and **1-N** relationships.

## What?
I coded a simple library to help you store objects by modifying your project as less as possible. 

The library uses **SQLite** to store the objects. There is just one table that saves the object *id*, its *class* and its *value*. The objects are serialized with **Gson**. You can provide an id, or you can let the library retrieve one by reflection. By default, the library tries to invoke a **getId()** method and searchs for **id** and **mId** fields.

There is a **@Id** annotation (Did I say I don't like to add extra annotations to my data model?) too. Optionally, you can use this annotation to indicate which is the field of your class that identifies the object.

Everything works synchronously, so it's not a good idea to use the library from the main thread of your Android app. I use it as DataSource combined with a RepositoryPattern.


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
compile 'com.martinchamarro.lazystorage:lazystorage:1.1.0'
~~~

####Please, let me know if you use LazyStorage!!! ;D
