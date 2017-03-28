# rabbitmq-consumers-and-ff4j
Nowadays, FF4J provides plant features but none of them enable you to turn on and off rabbit consumers. It means that if you just do not want to consume a temporary queue for any reason (not missing the messages) you can not. One approach to work around this scenario would be throwing an exception and make the message go back to the queue. Another approach could be lock that thread until the feature is enabled again (there could be a lot of other ways). In both scenarios it wastes processing and may cause the idea that something is wrong whereas it is not.

This application purposes a implementation that enables and disables rabbit consumers through [FF4J](http://ff4j.org/) framework - Guaranteeing even distributed environments.

Currently, the tasks foreseen are:
- [x] Setup environment with rabbit and ff4j
- [x] Intercept FF4J calls to enable and disable features
- [x] Enable and Disable consumers of the current instance according to FF4J features and queue names
- [ ] Replicate queue status to other instances of the application
- [ ] Create component easy to import in other projects
