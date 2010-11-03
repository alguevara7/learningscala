package net.alexguev.learningscala.iprovingchangenotifier

object Launcher {
	
	def main(args: Array[String]): Unit = {
		new Notifier("alexei.guevara").notifyIfNecessary
	}
	
}