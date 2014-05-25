public class Utils {
	public static int typeIndex = 0; // 0 = Subject | 1 = Room | 2 = Commentary
	public static String language = "en";
	public static int langId = 0; // 0 = English || 1 = Français
	static String type[][] = { { "Subject", "Room", "Commentary" }, { "Matière", "Salle", "Commentaire" } };
	static String[] textHelp = {
			"<html><b>Use this program :</b><br>1] Add your subject/room/commentary by Id avaible in top bar. <br>2] Put your id in cell using right click.<br>3] Put the folder 'HyperGPSDeLaMortXDPtdrLol' of your desktop on your SD card of android smartphone.<br><br><b>Use the informations at the right of the window.</b><br>- For groups and weeks : 0 = all, 1 = A, 2 = B, 3 = 1, 4 = 2, 5 = 3.<br>- For the rest, numbers are ids of the value.</html>",
			"<html><b><Utilisation du programme :</b><br>1] Ajouter vos matières/salles/commentaires grâce aux ID disponible dans la barre du haut.<br>2]Ajouter vos ID dans les cellules du tableau en faisant un clic droit dessus.<br>3] Copier le dossier 'HyperGPSDeLaMortXDPtdrLol' de votre bureau sur votre carte SD de smartphone.<br><br><b>Utiliser les informations sur la droite de la fenêtre.</b><br>- Pour les groupes et semaines :  0 = tous/toutes, 1 = A, 2 = B, 3 = 1, 4 = 2, 5 = 3.<br>- Pour le reste, les nombres correspondent à l'id de la valeur.</html>" };
	static boolean existSubject = false, existRoom = false, existCommentary = false;
	public static String[] sList, rList, cList;
	public static int[] s, r, c;
}