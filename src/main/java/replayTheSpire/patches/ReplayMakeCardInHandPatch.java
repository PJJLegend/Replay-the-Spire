package replayTheSpire.patches;

import java.util.*;
import replayTheSpire.*;
import com.badlogic.gdx.graphics.*;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.core.*;
import com.megacrit.cardcrawl.relics.*;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.powers.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import basemod.*;
import com.evacipated.cardcrawl.modthespire.lib.*;

@SpirePatch(cls = "com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction", method = "update")
public class ReplayMakeCardInHandPatch {
	
	public static void Prefix(MakeTempCardInHandAction __Instance) {
		if (__Instance != null && AbstractDungeon.player != null && __Instance.amount != 0 && !__Instance.isDone) {
			if (AbstractDungeon.player.hasRelic(QuantumEgg.ID)) {
                final AbstractCard c = (AbstractCard)ReflectionHacks.getPrivate((Object)__Instance, (Class)MakeTempCardInHandAction.class, "c");
				if (c != null && c.canUpgrade()) {
					c.upgrade();
					ReplayTheSpireMod.BypassStupidBasemodRelicRenaming_flashRelic(QuantumEgg.ID);
				}
            }
		}
	}
	
}