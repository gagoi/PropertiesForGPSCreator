public class Utils {
	public static int typeIndex = 0; // 0 = Subject | 1 = Room | 2 = Commentary
	public static String language = "en";
	public static int langId = 0; // 0 = English || 1 = Français
	static String type[][] = { { "Subject", "Room", "Commentary" }, { "Matière", "Salle", "Commentaire" } };
	static String[] textHelp = {
			"<html><b>Use this program :</b><br>1] Add your subject/room/commentary by Id avaible in top bar. <br>2] Put your id in cell using right click.<br>3] Put the folder 'HyperGPSDeLaMortXDPtdrLol' of your desktop on your SD card of android smartphone.</html>",
			"<html><b><Utilisation du programme :</b><br>1] Ajouter vos matières/salles/commentaires grâce aux ID disponible dans la barre du haut.<br>2]Ajouter vos ID dans les cellules du tableau en faisant un clic droit dessus.<br>3] Copier le dossier 'HyperGPSDeLaMortXDPtdrLol' de votre bureau sur votre carte SD de smartphone.</html>" };
	static boolean existSubject = false, existRoom = false, existCommentary = false;
}