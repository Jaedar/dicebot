package dow.codex.ircbot.commands;

import dow.codex.ircbot.util.Logger;

@Deprecated
public class CommandLoader {
	@SuppressWarnings("unchecked")
	public <T> T getCommand(Class<T> iface, String commandName) {

		String className= iface.getPackage()+"."+commandName;
		Logger.debug("Classname to loadTables is "+ className);
		Class<?> loadedClass;
		
		try {
			iface.getClass();
			iface.getClass().getClassLoader();
			loadedClass = this.getClass().getClassLoader().loadClass(className);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			Logger.debug("Tried to Load nonexisting class, "+e.getMessage()+e.getCause() );
			e.printStackTrace();
			Logger.debug("Attempting to Continue with default class");
			loadedClass = DefaultCommand.class;
		}
		
		Class<T> castedClass = (Class<T>) loadedClass;
		T  instance ;
		try {
			instance = castedClass.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			instance = (T) new DefaultCommand();
		}
		
		return instance;
	}
	

	}
