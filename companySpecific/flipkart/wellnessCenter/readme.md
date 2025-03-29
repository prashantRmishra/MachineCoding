I was asked to design a Mind Wellness Center
It can have multiple Activities like: lectures, group therapies, support groups sessions, 1:1s etc.,

Features
Users should be able to see the list of events, list of available therapists.
Users should be able to book multiple events, search the events by event type, date

--------
User
Doctor(Metadata, List<Event>)
Event (LectureSession,GroupTherapySession, SupportGroupSession, OneOnOneSession)
Slot

Booking(User,Doctor, Event,Slot)
EventManager(crete,update,view)
UserManager
-------
