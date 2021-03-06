package character;

import entity.Player;
import entity.Time;
import render.IRenderable;
import render.Resource;

public class Kurosaki extends Character implements IRenderable {

	public Kurosaki(int p,Player player) {
		super(p,7, 100);
		indexC = 5;
		width = 44;
		height = 46;
		y = y - height;
		this.player = player;
		character = Resource.bleach.getSubimage(5, 27, 44, 46);
		transform();
	}

	@Override
	public void stand() {
		if (isRun || isJump || isAttack || isShoot || isSuperAttack)
			return;
		character = Resource.bleach.getSubimage(5, 27, 44, 46);
		for (int a : countPic)
			a = 0;
	}

	@Override
	public void picRunUpdate() {
		if (!isRun)
			return;
		isAttack = false;
		if (countPic[0] == 0)
			character = Resource.bleach.getSubimage(6, 108, 49, 37);
		else if (countPic[0] == 1)
			character = Resource.bleach.getSubimage(130, 108, 50, 39);
		else if (countPic[0] == 2)
			character = Resource.bleach.getSubimage(263, 107, 47, 39);
		else if (countPic[0] == 3)
			character = Resource.bleach.getSubimage(329, 109, 48, 36);
		else if (countPic[0] == 4)
			character = Resource.bleach.getSubimage(395, 109, 47, 37);
		else if (countPic[0] == 5)
			character = Resource.bleach.getSubimage(457, 109, 47, 37);
		countPic[0]++;
		if (countPic[0] == 6)
			countPic[0] = 0;
	}

	@Override
	public void picJumpUpdate(int i) {
		if (countPic[1] < 3) {
			if (countPic[1] == 0) {
				character = Resource.bleach.getSubimage(63, 176, 37, 43);
			} else if (countPic[1] == 1) {
				character = Resource.bleach.getSubimage(162, 177, 40, 36);
			} else if (countPic[1] == 2) {
				character = Resource.bleach.getSubimage(162, 177, 40, 36);
			}
		}
		if (count[i] == 1)
			countPic[1] = 0;
		else if (count[i] == jumpMax + 1)
			countPic[1] = 1;
		else if (count[i] == jumpMax * 2)
			countPic[1] = 2;
		else if (countPic[1] >= 2) {
			countPic[1] = 0;
			if (i == 0) {
				isJump = false;
			} else {
				isDoubleJump = false;
			}
		}
	}

	@Override
	public void picAttackUpdate() {
		if (!isAttack || isSuperAttack)
			return;
		if (countPic[2] == 0)
			character = Resource.bleach.getSubimage(7, 1192, 41, 42);
		else if (countPic[2] == 1)
			character = Resource.bleach.getSubimage(59, 1191, 41, 45);
		else if (countPic[2] == 2)
			character = Resource.bleach.getSubimage(112, 1189, 46, 47);
		else if (countPic[2] == 3)
			character = Resource.bleach.getSubimage(168, 1191, 41, 45);
		else if (countPic[2] == 4)
			character = Resource.bleach.getSubimage(219, 1181, 70, 55);
		else if (countPic[2] == 5)
			character = Resource.bleach.getSubimage(300, 1170, 48, 66);
		else if (countPic[2] == 6)
			character = Resource.bleach.getSubimage(363, 1178, 47, 58);
		else if (countPic[2] == 7)
			character = Resource.bleach.getSubimage(426, 1195, 46, 41);
		else if (countPic[2] == 8)
			character = Resource.bleach.getSubimage(485, 1191, 52, 45);
		countPic[2]++;
		if (countPic[2] >= 9) {
			countPic[2] = 0;
			isAttack = false;
			isDoubleAttack = false;
		}
	}

	@Override
	public void picShootUpdate() {
		if (!isShoot)
			return;
		if (countPic[5] == 0)
			character = Resource.bleach.getSubimage(5, 1192, 42, 44);
		else if (countPic[5] == 1)
			character = Resource.bleach.getSubimage(222, 1183, 68, 52);
		else if (countPic[5] == 2)
			character = Resource.bleach.getSubimage(301, 1172, 47, 64);
		else if (countPic[5] == 3)
			character = Resource.bleach.getSubimage(421, 1192, 51, 44);

		countPic[5]++;
		if (countPic[5] >= 4) {
			isShoot = false;
			countPic[5] = 0;
		}
	}

	@Override
	public void picSuperAttack() {
		if (!isSuperAttack)
			return;
		if (countPic[4] == 0)
			character = Resource.bleach.getSubimage(7, 2369, 37, 44);
		else if (countPic[4] == 1)
			character = Resource.bleach.getSubimage(52, 2371, 43, 42);
		else if (countPic[4] == 2)
			character = Resource.bleach.getSubimage(105, 2376, 44, 38);
		else if (countPic[4] == 3)
			character = Resource.bleach.getSubimage(158, 2377, 47, 36);
		else if (countPic[4] == 4)
			character = Resource.bleach.getSubimage(212, 2374, 49, 39);
		else if (countPic[4] == 5)
			character = Resource.bleach.getSubimage(272, 2373, 51, 40);
		else if (countPic[4] == 6)
			character = Resource.bleach.getSubimage(332, 2364, 42, 50);
		else if (countPic[4] == 7)
			character = Resource.bleach.getSubimage(384, 2365, 38, 48);
		else if (countPic[4] == 8)
			character = Resource.bleach.getSubimage(432, 2374, 68, 39);
		else if (countPic[4] == 9)
			character = Resource.bleach.getSubimage(506, 2374, 51, 40);
		else if (countPic[4] == 10)
			character = Resource.bleach.getSubimage(566, 2374, 57, 38);
		countPic[4]++;
		if (countPic[4] >= 11) {
			isAttack = false;
			isSuperAttack = false;
			Time.isAlreadyStop = false;
			countPic[4] = 0;
			isDoubleAttack = false;
		}
		if (countPic[4] >= 7 && countPic[4] < 11)
			isAttack = true;
	}

	@Override
	public void picLoseUpdate() {
		if (!lose)
			return;
		if (countPic[3] == 0)
			character = Resource.bleach.getSubimage(297, 352, 47, 32);
		else if (countPic[3] == 1)
			character = Resource.bleach.getSubimage(356, 353, 43, 34);
		else if (countPic[3] >= 2)
			character = Resource.bleach.getSubimage(443, 383, 57, 17);
		countPic[3]++;
	}

	@Override
	public int getZ() {
		return 0;
	}
}
